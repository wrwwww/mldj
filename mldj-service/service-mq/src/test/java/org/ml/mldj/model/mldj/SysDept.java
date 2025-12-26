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
 * 部门表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_dept", schema = "mailang")
public class SysDept {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 部门名称
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 父部门ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 部门路径
     */
    @Size(max = 255)
    @Column(name = "tree_path")
    private String treePath;

    /**
     * 排序值
     */
    @Column(name = "sort_value")
    private Integer sortValue;

    /**
     * 负责人
     */
    @Size(max = 100)
    @Column(name = "leader", length = 100)
    private String leader;

    /**
     * 联系电话
     */
    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

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