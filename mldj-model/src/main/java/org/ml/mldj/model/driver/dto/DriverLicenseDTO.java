package org.ml.mldj.model.driver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DriverLicenseDTO {
    /**
     * 驾驶证正面照片
     */
    @Schema(description = "驾驶证正面照片")
    private String driverLicenseFrontUrl;

    /**
     * 驾驶证背面照片
     */
    @Schema(description = "驾驶证背面照片")
    private String driverLicenseBackUrl;

    /**
     * 手持驾驶证照片
     */
    @Schema(description = "手持驾驶证照片")
    private String driverLicenseHandUrl;

}
