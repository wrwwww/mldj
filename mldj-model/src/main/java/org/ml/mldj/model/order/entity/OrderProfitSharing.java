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
 * 订单分润明细表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("order_profit_sharing")
@Schema(name = "OrderProfitSharing", description = "订单分润明细表")
public class OrderProfitSharing implements Serializable {

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
     * 分润规则ID
     */
    @Schema(description = "分润规则ID")
    private Long ruleId;

    /**
     * 订单金额
     */
    @Schema(description = "订单金额")
    private BigDecimal orderAmount;

    /**
     * 支付费率
     */
    @Schema(description = "支付费率")
    private BigDecimal paymentRate;

    /**
     * 支付手续费
     */
    @Schema(description = "支付手续费")
    private BigDecimal paymentFee;

    /**
     * 司机税率
     */
    @Schema(description = "司机税率")
    private BigDecimal driverTaxRate;

    /**
     * 司机税费
     */
    @Schema(description = "司机税费")
    private BigDecimal driverTaxFee;

    /**
     * 平台收入
     */
    @Schema(description = "平台收入")
    private BigDecimal platformIncome;

    /**
     * 司机收入
     */
    @Schema(description = "司机收入")
    private BigDecimal driverIncome;

    /**
     * 分润状态：0-待分润，1-已分润
     */
    @Schema(description = "分润状态：0-待分润，1-已分润")
    private Integer status;

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
