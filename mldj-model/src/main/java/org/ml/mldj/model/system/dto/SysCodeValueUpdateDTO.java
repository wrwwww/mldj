package org.ml.mldj.model.system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysCodeValueUpdateDTO extends SysCodeValueDTO {

    @NotNull(message = "[]不能为空")
    private String id;
}
