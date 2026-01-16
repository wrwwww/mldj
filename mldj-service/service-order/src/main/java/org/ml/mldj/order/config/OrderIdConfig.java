package org.ml.mldj.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "order.id")
@Configuration
@Data
public class OrderIdConfig {
    /**
     * 业务前缀
     */
    private String bizPrefix = "DD";

    /**
     * 数据中心ID (0-31)
     */
    private Long dataCenterId = 1L;

    /**
     * 机器ID (0-31)
     */
    private Long machineId = 1L;

    /**
     * 起始时间戳（用于减少ID长度）
     */
    private String startTimestamp = "2024-01-01 00:00:00";

    /**
     * 序列号位数
     */
    private Long sequenceBits = 12L;

    /**
     * 机器ID位数
     */
    private Long machineBits = 5L;

    /**
     * 数据中心位数
     */
    private Long dataCenterBits = 5L;
}