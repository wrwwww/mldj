package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 订单取消规则表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_cancel_rule", schema = "hxds_rule")
public class TbCancelRule {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 规则编码
     */
    @Size(max = 50)
    @NotNull
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    /**
     * 规则名称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    /**
     * 规则代码
     */
    @NotNull
    @Lob
    @Column(name = "rule", nullable = false)
    private String rule;

    /**
     * 1司机取消规则，2乘客取消规则
     */
    @NotNull
    @Column(name = "type", nullable = false)
    private Byte type;

    /**
     * 状态代码，1有效，2关闭
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 添加时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}