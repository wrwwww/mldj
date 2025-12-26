package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 司机禁闭表（禁止接单）
 */
@Getter
@Setter
@Entity
@Table(name = "tb_driver_lockdown", schema = "hxds_dr")
public class TbDriverLockdown {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @NotNull
    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    /**
     * 原因
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "reason", nullable = false, length = 200)
    private String reason;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 起始日期
     */
    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    /**
     * 天数
     */
    @NotNull
    @Column(name = "days", nullable = false)
    private Short days;

}