package org.ml.mldj.model.driver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverLicenseInfoDTO {
    /**
     * 驾驶证准驾车型
     */
    @Schema(description = "驾驶证准驾车型")
    private String driverLicenseClass;

    /**
     * 驾驶证号码
     */
    @Schema(description = "驾驶证号码")
    private String driverLicenseNo;

    /**
     * 驾驶证有效期
     */
    @Schema(description = "驾驶证有效期")
    private LocalDateTime driverLicenseExpire;

    /**
     * 驾驶证初次领证日期
     */
    @Schema(description = "驾驶证初次领证日期")
    private LocalDateTime driverLicenseIssueDate;
}
