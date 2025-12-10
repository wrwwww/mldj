package org.ml.mldj.driver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@MapperScan("org.ml.mldj.driver.mapper")
@ComponentScan("org.ml.mldj.*")
@SpringBootApplication
@EnableFeignClients
public class ServiceDriverApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverApplication.class, args);
    }
}
