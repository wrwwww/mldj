package org.ml.mldj.model.driver.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class DriverLicenseResult {
    // 驾驶证
    private String licenseNumber;
    private String licenseType;
    private boolean valid;
    private LocalDate expireDate;
}
