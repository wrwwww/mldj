package org.ml.mldj.rules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceRuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRuleApplication.class, args);
    }
}
