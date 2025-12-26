package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 费用规则表
 */
@Getter
@Setter
@Entity
@Table(name = "fee_rule", schema = "mailang")
public class FeeRule {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 规则名称
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 规则内容
     */
    @Lob
    @Column(name = "rule")
    private String rule;

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