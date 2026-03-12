package org.ml.mldj.model.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 码表类型表
 *
 * @TableName sys_code_type
 */
@Getter
@NoArgsConstructor
@Setter
public class SysCodeTypeDTO implements Serializable {


    @NotBlank(message = "[码表类型唯一标识，如 role/status/category]不能为空")

    @Size(max = 50, message = "编码长度不能超过50")
    private String codeType;
    /**
     * 码表类型名称，如 用户角色/文章状态/分类类型
     */
    @NotBlank(message = "[码表类型唯一标识，如 role/status/category]不能为空")

    @Size(max = 100, message = "编码长度不能超过50")
    private String name;
    /**
     * 说明
     */

    @Size(max = 255, message = "编码长度不能超过50")
    private String description;


}
