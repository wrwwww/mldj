package org.ml.mldj.model.driver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverRegistrationForm {
    @NotBlank(message = "Driver name cannot be blank")
    private String name;

    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotBlank(message = "License number cannot be blank")
    private String licenseNumber;

    @NotNull(message = "Openid cannot be null")
    private String openid;

}