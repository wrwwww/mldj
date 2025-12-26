package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_permission", schema = "hxds_mis")
public class TbPermission {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    /**
     * 权限
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "permission_name", nullable = false, length = 200)
    private String permissionName;

    /**
     * 模块ID
     */
    @Column(name = "module_id", columnDefinition = "int UNSIGNED not null")
    private Long moduleId;

    /**
     * 行为ID
     */
    @Column(name = "action_id", columnDefinition = "int UNSIGNED not null")
    private Long actionId;

}