package org.ml.mldj.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Lister {
    @RabbitListener(queues ="order.create")
    public void listen(String msg) {
        System.out.println("listen1-收到消息");
        System.out.println(msg);
    }
    @RabbitListener(queues ="order.create")
    public void listen2(String msg) {
        System.out.println("listen2-收到消息");
        System.out.println(msg);
    }

    @RabbitListener(queues ="order.create1")
    public void listen3(String msg) {
        System.out.println("listen3-收到消息");
        System.out.println(msg);
    }
    @RabbitListener(queues ="order.create1")
    public void listen4(String msg) {
        System.out.println("listen4-收到消息");
        System.out.println(msg);
    }
}
