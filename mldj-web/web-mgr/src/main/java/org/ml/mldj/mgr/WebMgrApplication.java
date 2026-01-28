package org.ml.mldj.mgr;

import org.ml.mldj.common.config.RustfsS3AutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import software.amazon.awssdk.services.s3.S3Client;


@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.ml.mldj.*")
@SpringBootApplication(exclude = {
        RustfsS3AutoConfiguration.class
})
public class WebMgrApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMgrApplication.class, args);
    }
}
