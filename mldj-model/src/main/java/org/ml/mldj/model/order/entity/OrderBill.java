package org.ml.mldj.model.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单账单明细表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("order_bill")
@Schema(name = "OrderBill", description = "订单账单明细表")
public class OrderBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    private Long orderId;

    /**
     * 费用规则ID
     */
    @Schema(description = "费用规则ID")
    private Long feeRuleId;

    /**
     * 总金额
     */
    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    /**
     * 支付金额
     */
    @Schema(description = "支付金额")
    private BigDecimal payAmount;

    /**
     * 里程费
     */
    @Schema(description = "里程费")
    private BigDecimal distanceFee;

    /**
     * 等待费
     */
    @Schema(description = "等待费")
    private BigDecimal waitFee;

    /**
     * 路桥费
     */
    @Schema(description = "路桥费")
    private BigDecimal tollFee;

    /**
     * 停车费
     */
    @Schema(description = "停车费")
    private BigDecimal parkingFee;

    /**
     * 其他费用
     */
    @Schema(description = "其他费用")
    private BigDecimal otherFee;

    /**
     * 长途费
     */
    @Schema(description = "长途费")
    private BigDecimal longDistanceFee;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal favourFee;

    /**
     * 奖励金额
     */
    @Schema(description = "奖励金额")
    private BigDecimal rewardFee;

    /**
     * 奖励规则ID
     */
    @Schema(description = "奖励规则ID")
    private Long rewardRuleId;

    /**
     * 优惠券金额
     */
    @Schema(description = "优惠券金额")
    private BigDecimal couponAmount;

    /**
     * 基础里程（公里）
     */
    @Schema(description = "基础里程（公里）")
    private BigDecimal baseDistance;

    /**
     * 基础里程费
     */
    @Schema(description = "基础里程费")
    private BigDecimal baseDistanceFee;

    /**
     * 超出里程（公里）
     */
    @Schema(description = "超出里程（公里）")
    private BigDecimal exceedDistance;

    /**
     * 超出里程单价
     */
    @Schema(description = "超出里程单价")
    private BigDecimal exceedDistancePrice;

    /**
     * 基础等待时间（分钟）
     */
    @Schema(description = "基础等待时间（分钟）")
    private Integer baseWaitMinute;

    /**
     * 超出等待时间（分钟）
     */
    @Schema(description = "超出等待时间（分钟）")
    private Integer exceedWaitMinute;

    /**
     * 超出等待时间单价
     */
    @Schema(description = "超出等待时间单价")
    private BigDecimal exceedWaitMinutePrice;

    /**
     * 基础长途里程（公里）
     */
    @Schema(description = "基础长途里程（公里）")
    private BigDecimal baseLongDistance;

    /**
     * 超出长途里程（公里）
     */
    @Schema(description = "超出长途里程（公里）")
    private BigDecimal exceedLongDistance;

    /**
     * 超出长途里程单价
     */
    @Schema(description = "超出长途里程单价")
    private BigDecimal exceedLongDistancePrice;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Schema(description = "逻辑删除标志 0=未删除 1=已删除")
    private Boolean isDeleted;
}
