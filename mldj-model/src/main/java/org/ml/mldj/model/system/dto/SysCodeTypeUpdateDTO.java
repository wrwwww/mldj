package org.ml.mldj.model.system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 码表类型表
 *
 * @TableName sys_code_type
 */
@Getter
@NoArgsConstructor
@Setter
public class SysCodeTypeUpdateDTO extends SysCodeTypeDTO {


    @NotNull(message = "[]不能为空")
    private String id;

}
