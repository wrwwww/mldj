package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

/**
 * 管理系统用户表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_user", schema = "hxds_mis")
public class TbUser {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "username", nullable = false, length = 200)
    private String username;

    /**
     * 密码（AES加密）
     */
    @Size(max = 2000)
    @NotNull
    @Column(name = "password", nullable = false, length = 2000)
    private String password;

    /**
     * 姓名
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    /**
     * 性别
     */
    @NotNull
    @Column(name = "sex", nullable = false)
    private Character sex;

    /**
     * 电话
     */
    @Size(max = 11)
    @Column(name = "tel", length = 11)
    private String tel;

    /**
     * 邮箱
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "email", nullable = false, length = 200)
    private String email;

    /**
     * 角色
     */
    @NotNull
    @Column(name = "role", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> role;

    /**
     * 是否为超级管理员
     */
    @NotNull
    @Column(name = "root", nullable = false)
    private Byte root;

    /**
     * 部门编号
     */
    @NotNull
    @Column(name = "dept_id", nullable = false)
    private Integer deptId;

    /**
     * 1有效，2离职，3禁用
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 创建日期
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}