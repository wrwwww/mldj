package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 用户表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user", schema = "mailang")
public class SysUser {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Size(max = 100)
    @Column(name = "username", length = 100)
    private String username;

    /**
     * 密码
     */
    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    /**
     * 姓名
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 手机号
     */
    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * 头像URL
     */
    @Size(max = 255)
    @Column(name = "head_url")
    private String headUrl;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 岗位ID
     */
    @Column(name = "post_id")
    private Long postId;

    /**
     * 描述
     */
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    /**
     * 状态：0-禁用，1-启用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}