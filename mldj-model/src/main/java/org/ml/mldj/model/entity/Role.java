package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_role")
@Schema(name = "Role", description = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 权限集合
     */
    @Schema(description = "权限集合")
    private String permissions;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String desc;

    /**
     * 系统角色内置权限
     */
    @Schema(description = "系统角色内置权限")
    private String defaultPermissions;

    /**
     * 是否为系统内置角色
     */
    @Schema(description = "是否为系统内置角色")
    private Boolean systemic;
}
