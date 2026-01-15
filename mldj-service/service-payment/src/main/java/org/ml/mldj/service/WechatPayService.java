package org.ml.mldj.service;

import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import org.ml.mldj.config.WechatPayConfig;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.ml.mldj.model.payments.dto.WechatPayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

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



}
