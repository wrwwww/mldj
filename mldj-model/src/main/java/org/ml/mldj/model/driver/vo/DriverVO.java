package org.ml.mldj.model.driver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverVO {
    private Long id;
    private String name;
    private String phone;
    private String licenseNumber;
    private Integer workStatus;

}