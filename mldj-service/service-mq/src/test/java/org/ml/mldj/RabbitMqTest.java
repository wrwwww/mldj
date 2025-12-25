package org.ml.mldj;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMqTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void test() {
        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.convertAndSend("order.exchange", "", i, msg -> {
////                msg.getMessageProperties().setHeader("content-type", "application/json");
//                return msg;
//            });
            rabbitTemplate.convertAndSend("order.create2",i);

        }

    }
}
