package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 司机罚款表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_driver_fine", schema = "hxds_dr")
public class TbDriverFine {
    /**
     * 主键ID
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
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 罚款金额
     */
    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    /**
     * 备注信息
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "remark", nullable = false, length = 200)
    private String remark;

    /**
     * 1未缴纳，2已缴纳
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 创建时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}