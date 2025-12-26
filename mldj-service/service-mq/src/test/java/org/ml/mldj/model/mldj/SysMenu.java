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
 * 菜单表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_menu", schema = "mailang")
public class SysMenu {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 父菜单ID
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 菜单类型：1-目录，2-菜单，3-按钮
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 路由路径
     */
    @Size(max = 255)
    @Column(name = "path")
    private String path;

    /**
     * 组件路径
     */
    @Size(max = 255)
    @Column(name = "component")
    private String component;

    /**
     * 权限标识
     */
    @Size(max = 255)
    @Column(name = "perms")
    private String perms;

    /**
     * 菜单图标
     */
    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    /**
     * 排序值
     */
    @Column(name = "sort_value")
    private Integer sortValue;

    /**
     * 状态：0-禁用，1-启用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 激活菜单
     */
    @Size(max = 255)
    @Column(name = "active_menu")
    private String activeMenu;

    /**
     * 是否隐藏：0-显示，1-隐藏
     */
    @Column(name = "is_hide")
    private Integer isHide;

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