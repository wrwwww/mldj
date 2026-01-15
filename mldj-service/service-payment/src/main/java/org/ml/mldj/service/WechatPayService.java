package org.ml.mldj.service;

import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.ml.mldj.config.WechatPayConfig;
import org.ml.mldj.model.payments.dto.WechatPayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WechatPayService {
    @Autowired
    private JsapiServiceExtension jsapiServiceExtension;

    @Autowired
    private WechatPayConfig wechatPayConfig;

    /**
     * 创建预支付订单
     */
    public PrepayWithRequestPaymentResponse createPrepayOrder(WechatPayOrder order) {


        PrepayRequest request = new PrepayRequest();
        // 金额（单位：分）
        Amount amount = new Amount();
        amount.setCurrency("CNY");
        amount.setTotal(100);
        request.setAmount(amount);
        request.setAppid(wechatPayConfig.getAppId());
        request.setMchid(wechatPayConfig.getMchId());
        request.setDescription(order.getDescription());
        request.setNotifyUrl(wechatPayConfig.getNotifyUrl());
        // 我们生成的打车id
        request.setOutTradeNo(order.getOrderId());
        Payer payer = new Payer();
        payer.setOpenid(order.getPayer().getOpenid());
        request.setPayer(payer);
        // 设置超时时间
        if (order.getTimeExpire() != null) {
            request.setTimeExpire(order.getTimeExpire());
        }
        // 调用下单方法，得到应答
        return jsapiServiceExtension.prepayWithRequestPayment(request);

    }


    public ResponseEntity<?> process(HttpServletRequest request) {
        String serial = request.getHeader("Wechatpay-Serial");
        String signature = request.getHeader("Wechatpay-Signature");
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");

        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(serial)
                .nonce(nonce)
                .signature(signature)
                .timestamp(timestamp)
                .body(request.getInputStream().toString())
                .build();
        return ResponseEntity.ok().build();
    }
}
