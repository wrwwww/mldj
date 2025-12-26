package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 司机设置表
 */
@Getter
@Setter
@Entity
@Table(name = "driver_set", schema = "mailang")
public class DriverSet {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 服务状态：0-下班，1-上班
     */
    @Column(name = "service_status")
    private Integer serviceStatus;

    /**
     * 接单距离（公里）
     */
    @Column(name = "order_distance", precision = 18, scale = 2)
    private BigDecimal orderDistance;

    /**
     * 可接单距离（公里）
     */
    @Column(name = "accept_distance", precision = 18, scale = 2)
    private BigDecimal acceptDistance;

    /**
     * 是否自动接单：0-否，1-是
     */
    @Column(name = "is_auto_accept")
    private Integer isAutoAccept;

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