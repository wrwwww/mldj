package org.ml.mldj.model.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 码表值表
 *
 * @TableName sys_code_value
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("sys_code_value")
public class SysCodeValue implements Serializable {

    /**
     *
     */
    @NotNull(message = "[]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    /**
     * 码表类型ID，外键引用 sys_code_type.id
     */
    @NotNull(message = "[码表类型ID，外键引用 sys_code_type.id]不能为空")
    @Schema(description = "码表类型ID，外键引用 sys_code_type.id")
    private String codeType;
    /**
     * 实际存储值，如 admin/draft/published
     */
    @NotBlank(message = "[实际存储值，如 admin/draft/published]不能为空")
    @Size(max = 50, message = "编码长度不能超过50")
    @Schema(description = "实际存储值，如 admin/draft/published")
    private String codeValue;
    /**
     * 显示名称，如 管理员/草稿/已发布
     */
    @NotBlank(message = "[显示名称，如 管理员/草稿/已发布]不能为空")
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "显示名称，如 管理员/草稿/已发布")
    private String codeLabel;
    /**
     * 备注
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @Schema(description = "备注")
    private String description;
    /**
     * 排序，用于前端显示顺序
     */
    @Schema(description = "排序，用于前端显示顺序")
    private Integer sortOrder;
    /**
     * 是否启用，0=禁用,1=启用
     */
    @Schema(description = "是否启用，0=禁用,1=启用")
    private Integer isActive;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createdAt;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updatedAt;


}
