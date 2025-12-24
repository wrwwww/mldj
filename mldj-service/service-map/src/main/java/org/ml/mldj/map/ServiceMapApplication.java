package org.ml.mldj.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.ml.mldj.map.*")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class, args);
    }
}
