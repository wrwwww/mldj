package org.ml.mldj.receiver;

import org.ml.mldj.common.constant.MqConst;
import org.ml.mldj.order.client.OrderFeignClient;
import org.ml.mldj.service.PaymentInfoService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentInfoListener {
    @Autowired
    PaymentInfoService paymentInfoService;
@Autowired
OrderFeignClient orderFeignClient;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_ORDER_CREATED,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_ORDER),
            key = {MqConst.ROUTING_PAY_SUCCESS}
    ))
    public void receive(String orderNo) {
        //  调用更新订单状态为完成
       orderFeignClient.orderPaymentSuccess(orderNo);

    }
}
