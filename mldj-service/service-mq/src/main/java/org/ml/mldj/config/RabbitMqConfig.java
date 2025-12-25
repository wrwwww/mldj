package org.ml.mldj.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("order.exchange").build();
    }

    @Bean
    public Queue queue() {
        return new Queue("order.create");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public Queue queue1(){
        return new Queue("order.create1");
    }

}
