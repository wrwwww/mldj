package org.ml.mldj.map.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gaode")
@Getter
@Setter
public class GaoDeConfig {
    private String key;
    private String geocodeUrl;
    private String drivingUrl;
}
