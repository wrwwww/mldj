package org.ml.mldj.listener;

import org.ml.mldj.common.constant.MqConst;
import org.ml.mldj.model.customer.dto.SendNewOrderMessageForm;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class orderCreateListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_ORDER_CREATED,durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_ORDER),
            key = {MqConst.ROUTING_PAY_SUCCESS}
    ))
    public void orderCreated(SendNewOrderMessageForm form) {
        // 将消息发送给form中的司机


    }

}
