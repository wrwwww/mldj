package org.ml.mldj.model.driver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DriverWorkStatusUpdateForm {
    @NotNull(message = "Work status cannot be null")
    private Integer workStatus;

}