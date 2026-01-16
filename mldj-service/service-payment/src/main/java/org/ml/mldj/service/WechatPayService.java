package org.ml.mldj.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.constant.MqConst;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.config.WechatPayConfig;
import org.ml.mldj.mapper.PaymentInfoMapper;
import org.ml.mldj.model.payments.dto.WechatPayOrder;
import org.ml.mldj.model.payments.entity.PaymentInfo;
import org.ml.mldj.model.payments.vo.WxPrepayVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Service
public class WechatPayService {
    @Autowired
    private JsapiServiceExtension jsapiServiceExtension;

    @Autowired
    private WechatPayConfig wechatPayConfig;
    @Autowired
    private RSAAutoCertificateConfig rsaAutoCertificateConfig;
    @Autowired
    PaymentInfoMapper paymentInfoMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 创建预支付订单
     */
    public WxPrepayVo createPrepayOrder(WechatPayOrder order) {
        LambdaQueryWrapper<PaymentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentInfo::getOrderNo, order.getOrderId());
        PaymentInfo paymentInfo = paymentInfoMapper.selectById(queryWrapper);
        if (paymentInfo == null) {
            // 创建订单
            paymentInfo = new PaymentInfo();
            paymentInfo.setOrderNo(order.getOrderId());
            paymentInfo.setAmount(order.getAmount());
            paymentInfo.setContent(order.getDescription());
            paymentInfoMapper.insert(paymentInfo);
        }

        try {
            PrepayWithRequestPaymentResponse response = prepayWithRequestPayment(paymentInfo);
            WxPrepayVo wxPrepayVo = new WxPrepayVo();
            BeanUtils.copyProperties(response, wxPrepayVo);
            wxPrepayVo.setTimeStamp(response.getTimeStamp());
            return wxPrepayVo;
        } catch (Exception e) {
            throw new BizException("支付发起异常");
        }
    }

    /**
     * JSAPI支付下单，并返回JSAPI调起支付数据
     */
    public PrepayWithRequestPaymentResponse prepayWithRequestPayment(PaymentInfo paymentInfo) {
        // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();
        // 金额（单位：分）
        request.setAmount(fromBigDecimal(paymentInfo.getAmount()));
        request.setAppid(wechatPayConfig.getAppId());
        request.setMchid(wechatPayConfig.getMerchantId());
        request.setDescription(paymentInfo.getContent());
        request.setNotifyUrl(wechatPayConfig.getNotifyUrl());
        // 我们生成的打车id
        request.setOutTradeNo(paymentInfo.getOrderNo());
        Payer payer = new Payer();
        payer.setOpenid(paymentInfo.getCustomerOpenId());
        request.setPayer(payer);
        //是否指定分账，不指定不能分账
        SettleInfo settleInfo = new SettleInfo();
        settleInfo.setProfitSharing(true);
        request.setSettleInfo(settleInfo);
        // 调用下单方法，得到应答
        return jsapiServiceExtension.prepayWithRequestPayment(request);
    }

    /**
     * 从 BigDecimal 创建 Amount
     */
    public static Amount fromBigDecimal(BigDecimal amount) {
        Amount result = new Amount();
        result.setCurrency("CNY");
        result.setTotal(amount.multiply(new BigDecimal("100")).intValue());
        return result;
    }

    /**
     * 转换为 BigDecimal
     */
    public BigDecimal toBigDecimal(int total) {
        return new BigDecimal(total).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }

    public ResponseEntity<?> process(HttpServletRequest request) {
        String serial = request.getHeader("Wechatpay-Serial");
        String signature = request.getHeader("Wechatpay-Signature");
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        String body = readRequestData(request);
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(serial)
                .nonce(nonce)
                .signature(signature)
                .timestamp(timestamp)
                .body(body)
                .build();
        //3.初始化 NotificationParser
        NotificationParser parser = new NotificationParser(rsaAutoCertificateConfig);
        //4.以支付通知回调为例，验签、解密并转换成 Transaction
        Transaction transaction = parser.parse(requestParam, Transaction.class);
        if (null != transaction && transaction.getTradeState() == Transaction.TradeStateEnum.SUCCESS) {
            //5.处理支付业务
            handlePayment(transaction);
        }
        return ResponseEntity.ok().build();
    }

    @Transactional(rollbackFor = Exception.class)
    public void handlePayment(Transaction transaction) {
        String orderNo = transaction.getOutTradeNo();

        // 直接CAS更新，不先查询（最推荐的方式）
        PaymentInfo updateEntity = new PaymentInfo();
        updateEntity.setPaymentStatus(1);
        updateEntity.setTransactionId(transaction.getTransactionId());
        updateEntity.setCallbackTime(LocalDateTime.now());
        updateEntity.setCallbackContent(JSON.toJSONString(transaction));

        LambdaUpdateWrapper<PaymentInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(PaymentInfo::getOrderNo, orderNo)
                .eq(PaymentInfo::getPaymentStatus, 0); // 状态CAS

        int updatedRows = paymentInfoMapper.update(updateEntity, wrapper);

        if (updatedRows == 0) {
            // 更新失败，查询原因
            PaymentInfo existing = paymentInfoMapper.selectOne(
                    new LambdaQueryWrapper<PaymentInfo>()
                            .eq(PaymentInfo::getOrderNo, orderNo)
            );

            if (existing == null) {
                log.error("支付记录不存在，orderNo: {}", orderNo);
                throw new RuntimeException("支付记录不存在，orderNo: " + orderNo);
            } else if (existing.getPaymentStatus() == 1) {
                log.info("订单已支付，幂等返回，orderNo: {}", orderNo);
                return; // 幂等处理
            } else {
                throw new RuntimeException("支付状态更新失败，当前状态: " + existing.getPaymentStatus());
            }
        }

        // 发送MQ消息
        rabbitTemplate.convertAndSend(MqConst.EXCHANGE_ORDER,
                MqConst.ROUTING_PAY_SUCCESS,
                orderNo);
    }

    private String readRequestData(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = request.getReader();
            for (String line; (line = br.readLine()) != null; ) {
                if (!sb.isEmpty()) {
                    sb.append("\n");
                }
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean queryPayStatus(String orderNo) {

        QueryOrderByOutTradeNoRequest queryRequest = new QueryOrderByOutTradeNoRequest();
        queryRequest.setOutTradeNo(orderNo);
        queryRequest.setMchid(wechatPayConfig.getMerchantId());
        Transaction transaction = jsapiServiceExtension.queryOrderByOutTradeNo(queryRequest);
        if (null == transaction||transaction.getTradeState()!=Transaction.TradeStateEnum.SUCCESS) {
            return false;
        }
        handlePayment(transaction);
        return true;
    }
}
