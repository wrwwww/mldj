package org.ml.mldj.order.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMqConfig {
    @Bean
    public FanoutExchange orderExchange() {
        return ExchangeBuilder.fanoutExchange("order.exchange")
                .durable(true)
                .build();
    }

    // 订单创建队列
    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable("order.created.queue")
                .withArgument("x-dead-letter-exchange", "order.dlx.exchange")
                .withArgument("x-dead-letter-routing-key", "order.created.dlx")
                .withArgument("x-max-length", 10000)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(orderCreatedQueue()).to(orderExchange());
    }

    // 死信交换机（订单领域专用）
    @Bean
    public DirectExchange orderDlxExchange() {
        return new DirectExchange("order.dlx.exchange");
    }
}
