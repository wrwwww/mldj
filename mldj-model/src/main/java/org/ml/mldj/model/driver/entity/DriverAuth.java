package org.ml.mldj.model.driver.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.ml.mldj.model.driver.DriverAuthStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("driver_auth")
public class DriverAuth {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long driverId;

    private DriverAuthStatus status;

    // 身份证
    private String idName;
    private String idNumber;

    // 驾驶证
    private String licenseNumber;
    private String licenseType;
    private LocalDate licenseExpireAt;
    private Boolean licenseVerified;

    // 人脸
    private BigDecimal faceSimilarity;
    private BigDecimal livenessScore;

    private String verifyProvider;
    private String verifyTraceId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
