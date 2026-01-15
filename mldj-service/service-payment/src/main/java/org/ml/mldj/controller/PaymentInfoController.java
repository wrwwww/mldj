package org.ml.mldj.controller;

import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.ml.mldj.model.payments.dto.WechatPayOrder;
import org.ml.mldj.service.WechatPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 支付信息表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Controller
@RequestMapping("/paymentInfo")
public class PaymentInfoController {
    @Autowired
    WechatPayService wechatPayService;

    @Operation(description = "接受微信回调")
    @PostMapping("/callback/wechat/pay")
    public String notify(HttpServletRequest request) {

        PayNotify notify = parseAndVerify(request);

        Payment payment = paymentRepo.findByTradeNo(notify.getOutTradeNo());

        if (payment.isSuccess()) {
            return "SUCCESS";
        }

        payment.success();

        paymentRepo.save(payment);

        eventBus.publish(new PaySuccessEvent(payment.getOrderId()));

        return "SUCCESS";
    }

    @PostMapping("/create")
    public Result<PrepayWithRequestPaymentResponse> createPayment(WechatPayOrder orderInfo) {
        return Result.success(wechatPayService.createPrepayOrder(orderInfo));
    }
}
