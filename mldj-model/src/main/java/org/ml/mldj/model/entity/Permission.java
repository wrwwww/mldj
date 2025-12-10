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
 * 权限表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_permission")
@Schema(name = "Permission", description = "权限表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限
     */
    @Schema(description = "权限")
    private String permissionName;

    /**
     * 模块ID
     */
    @Schema(description = "模块ID")
    private Integer moduleId;

    /**
     * 行为ID
     */
    @Schema(description = "行为ID")
    private Integer actionId;
}
