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

import java.util.Map;

/**
 * 角色表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_role", schema = "hxds_mis")
public class TbRole {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    /**
     * 角色名称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 200)
    private String roleName;

    /**
     * 权限集合
     */
    @NotNull
    @Column(name = "permissions", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> permissions;

    /**
     * 描述
     */
    @Size(max = 200)
    @Column(name = "`desc`", length = 200)
    private String desc;

    /**
     * 系统角色内置权限
     */
    @Column(name = "default_permissions")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> defaultPermissions;

    /**
     * 是否为系统内置角色
     */
    @Column(name = "systemic")
    private Boolean systemic;

}