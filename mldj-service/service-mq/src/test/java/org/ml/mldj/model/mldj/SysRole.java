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
 * 角色表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role", schema = "mailang")
public class SysRole {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 角色名称
     */
    @Size(max = 100)
    @Column(name = "role_name", length = 100)
    private String roleName;

    /**
     * 角色编码
     */
    @Size(max = 100)
    @Column(name = "role_code", length = 100)
    private String roleCode;

    /**
     * 角色描述
     */
    @Size(max = 255)
    @Column(name = "description")
    private String description;

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