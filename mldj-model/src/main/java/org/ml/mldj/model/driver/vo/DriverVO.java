package org.ml.mldj.model.driver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverVO {
    private String id;
    private String name;
    private String phone;
    private String driverLicenseNo;
    private Integer workStatus;

}