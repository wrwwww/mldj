package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理系统用户表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_user")
@Schema(name = "User", description = "管理系统用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码（AES加密）
     */
    @Schema(description = "密码（AES加密）")
    private String password;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String sex;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String tel;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 角色
     */
    @Schema(description = "角色")
    private String role;

    /**
     * 是否为超级管理员
     */
    @Schema(description = "是否为超级管理员")
    private Byte root;

    /**
     * 部门编号
     */
    @Schema(description = "部门编号")
    private Integer deptId;

    /**
     * 1有效，2离职，3禁用
     */
    @Schema(description = "1有效，2离职，3禁用")
    private Byte status;

    /**
     * 创建日期
     */
    @Schema(description = "创建日期")
    private LocalDateTime createTime;
}
