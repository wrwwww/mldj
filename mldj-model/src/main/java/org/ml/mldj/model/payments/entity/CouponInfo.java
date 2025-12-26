package org.ml.mldj.model.payments.entity;

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
 * 优惠券信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("coupon_info")
@Schema(name = "CouponInfo", description = "优惠券信息表")
public class CouponInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 优惠卷类型 1 现金券 2 折扣
     */
    @Schema(description = "优惠卷类型 1 现金券 2 折扣")
    private Integer couponType;

    /**
     * 优惠卷名称
     */
    @Schema(description = "优惠卷名称")
    private String name;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal amount;

    /**
     * 折扣：取值[1 到 10]
     */
    @Schema(description = "折扣：取值[1 到 10]")
    private BigDecimal discount;

    /**
     * 使用门槛 0->没门槛
     */
    @Schema(description = "使用门槛 0->没门槛")
    private BigDecimal conditionAmount;

    /**
     * 发行数量
     */
    @Schema(description = "发行数量")
    private Integer publishCount;

    /**
     * 每人限领张数
     */
    @Schema(description = "每人限领张数")
    private Integer perLimit;

    /**
     * 已使用数量
     */
    @Schema(description = "已使用数量")
    private Integer useCount;

    /**
     * 领取数量
     */
    @Schema(description = "领取数量")
    private Integer receiveCount;

    /**
     * 过期时间
     */
    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

    /**
     * 优惠券描述
     */
    @Schema(description = "优惠券描述")
    private String description;

    /**
     * 状态[0-未发布，1-已发布，-1-已过期]
     */
    @Schema(description = "状态[0-未发布，1-已发布，-1-已过期]")
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
