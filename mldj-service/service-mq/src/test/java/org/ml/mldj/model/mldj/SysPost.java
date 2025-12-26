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
 * 岗位表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_post", schema = "mailang")
public class SysPost {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 岗位编码
     */
    @Size(max = 50)
    @Column(name = "post_code", length = 50)
    private String postCode;

    /**
     * 岗位名称
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 岗位描述
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