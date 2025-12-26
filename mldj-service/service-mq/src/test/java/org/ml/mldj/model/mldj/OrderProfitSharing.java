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
 * 订单分润明细表
 */
@Getter
@Setter
@Entity
@Table(name = "order_profit_sharing", schema = "mailang")
public class OrderProfitSharing {
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
     * 分润规则ID
     */
    @Column(name = "rule_id")
    private Long ruleId;

    /**
     * 订单金额
     */
    @Column(name = "order_amount", precision = 18, scale = 2)
    private BigDecimal orderAmount;

    /**
     * 支付费率
     */
    @Column(name = "payment_rate", precision = 18, scale = 4)
    private BigDecimal paymentRate;

    /**
     * 支付手续费
     */
    @Column(name = "payment_fee", precision = 18, scale = 2)
    private BigDecimal paymentFee;

    /**
     * 司机税率
     */
    @Column(name = "driver_tax_rate", precision = 18, scale = 4)
    private BigDecimal driverTaxRate;

    /**
     * 司机税费
     */
    @Column(name = "driver_tax_fee", precision = 18, scale = 2)
    private BigDecimal driverTaxFee;

    /**
     * 平台收入
     */
    @Column(name = "platform_income", precision = 18, scale = 2)
    private BigDecimal platformIncome;

    /**
     * 司机收入
     */
    @Column(name = "driver_income", precision = 18, scale = 2)
    private BigDecimal driverIncome;

    /**
     * 分润状态：0-待分润，1-已分润
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