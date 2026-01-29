package org.ml.mldj.order.listener;

import org.ml.mldj.common.constant.MqConst;
import org.ml.mldj.order.service.OrderInfoService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    @Autowired
    OrderInfoService orderInfoService;
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(MqConst.QUEUE_ORDER_CREATED),
//            exchange = @Exchange(MqConst.EXCHANGE_ORDER)
//    ))
//    public void orderCreated(OrderCreateForm form) {
//
//    }
}
