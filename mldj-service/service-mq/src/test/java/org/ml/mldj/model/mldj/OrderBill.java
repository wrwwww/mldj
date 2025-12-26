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
 * 订单账单明细表
 */
@Getter
@Setter
@Entity
@Table(name = "order_bill", schema = "mailang")
public class OrderBill {
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
     * 费用规则ID
     */
    @Column(name = "fee_rule_id")
    private Long feeRuleId;

    /**
     * 总金额
     */
    @Column(name = "total_amount", precision = 18, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 支付金额
     */
    @Column(name = "pay_amount", precision = 18, scale = 2)
    private BigDecimal payAmount;

    /**
     * 里程费
     */
    @Column(name = "distance_fee", precision = 18, scale = 2)
    private BigDecimal distanceFee;

    /**
     * 等待费
     */
    @Column(name = "wait_fee", precision = 18, scale = 2)
    private BigDecimal waitFee;

    /**
     * 路桥费
     */
    @Column(name = "toll_fee", precision = 18, scale = 2)
    private BigDecimal tollFee;

    /**
     * 停车费
     */
    @Column(name = "parking_fee", precision = 18, scale = 2)
    private BigDecimal parkingFee;

    /**
     * 其他费用
     */
    @Column(name = "other_fee", precision = 18, scale = 2)
    private BigDecimal otherFee;

    /**
     * 长途费
     */
    @Column(name = "long_distance_fee", precision = 18, scale = 2)
    private BigDecimal longDistanceFee;

    /**
     * 优惠金额
     */
    @Column(name = "favour_fee", precision = 18, scale = 2)
    private BigDecimal favourFee;

    /**
     * 奖励金额
     */
    @Column(name = "reward_fee", precision = 18, scale = 2)
    private BigDecimal rewardFee;

    /**
     * 奖励规则ID
     */
    @Column(name = "reward_rule_id")
    private Long rewardRuleId;

    /**
     * 优惠券金额
     */
    @Column(name = "coupon_amount", precision = 18, scale = 2)
    private BigDecimal couponAmount;

    /**
     * 基础里程（公里）
     */
    @Column(name = "base_distance", precision = 18, scale = 2)
    private BigDecimal baseDistance;

    /**
     * 基础里程费
     */
    @Column(name = "base_distance_fee", precision = 18, scale = 2)
    private BigDecimal baseDistanceFee;

    /**
     * 超出里程（公里）
     */
    @Column(name = "exceed_distance", precision = 18, scale = 2)
    private BigDecimal exceedDistance;

    /**
     * 超出里程单价
     */
    @Column(name = "exceed_distance_price", precision = 18, scale = 2)
    private BigDecimal exceedDistancePrice;

    /**
     * 基础等待时间（分钟）
     */
    @Column(name = "base_wait_minute")
    private Integer baseWaitMinute;

    /**
     * 超出等待时间（分钟）
     */
    @Column(name = "exceed_wait_minute")
    private Integer exceedWaitMinute;

    /**
     * 超出等待时间单价
     */
    @Column(name = "exceed_wait_minute_price", precision = 18, scale = 2)
    private BigDecimal exceedWaitMinutePrice;

    /**
     * 基础长途里程（公里）
     */
    @Column(name = "base_long_distance", precision = 18, scale = 2)
    private BigDecimal baseLongDistance;

    /**
     * 超出长途里程（公里）
     */
    @Column(name = "exceed_long_distance", precision = 18, scale = 2)
    private BigDecimal exceedLongDistance;

    /**
     * 超出长途里程单价
     */
    @Column(name = "exceed_long_distance_price", precision = 18, scale = 2)
    private BigDecimal exceedLongDistancePrice;

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