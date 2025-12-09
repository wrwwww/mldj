package org.ml.mldj.customer.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
@Component
@RefreshScope
public class NacosConfigTest {


        @NacosValue("${a.value}")
        private String configValue;

        @PostConstruct
        public void printConfig() {
            System.out.println("Loaded config from Nacos: " + configValue);
        }
}
