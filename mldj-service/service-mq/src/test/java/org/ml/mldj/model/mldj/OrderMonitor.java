package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 订单监控表
 */
@Getter
@Setter
@Entity
@Table(name = "order_monitor", schema = "mailang")
public class OrderMonitor {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 文件数量
     */
    @Column(name = "file_num")
    private Integer fileNum;

    /**
     * 审核数量
     */
    @Column(name = "audit_num")
    private Integer auditNum;

    /**
     * 是否报警：0-否，1-是
     */
    @Column(name = "is_alarm")
    private Integer isAlarm;

    /**
     * 监控状态
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