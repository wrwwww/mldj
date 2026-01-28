package org.ml.mldj.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.ml.mldj.*")
@SpringBootApplication
public class WebCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebCustomerApplication.class, args);
    }
}
