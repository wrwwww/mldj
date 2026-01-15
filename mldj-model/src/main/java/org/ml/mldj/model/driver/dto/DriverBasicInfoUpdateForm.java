package org.ml.mldj.model.driver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverBasicInfoUpdateForm {
    @NotBlank(message = "Driver name cannot be blank")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

}