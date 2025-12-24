package org.ml.mldj.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqConfig {
    private String host;

    private int port;

    private String username;

    private String password;
    @Bean
    public ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        // factory.setUsername(username);
        // factory.setPassword(password);
        return factory;
    }

}
