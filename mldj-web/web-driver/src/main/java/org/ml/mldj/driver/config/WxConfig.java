package org.ml.mldj.driver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WxConfig {
    @Value("${wechat.appid}")
    @Setter
    @Getter
    private String appid;  // 小程序的 AppID
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Setter
    @Getter
    @Value("${wechat.appsecret}")
    private String appsecret;  // 小程序的 AppSecret

}
