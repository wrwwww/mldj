package org.ml.mldj.model.payments.entity;

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
 * 支付信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("payment_info")
@Schema(name = "PaymentInfo", description = "支付信息表")
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户OpenID
     */
    @Schema(description = "客户OpenID")
    private String customerOpenId;

    /**
     * 司机OpenID
     */
    @Schema(description = "司机OpenID")
    private String driverOpenId;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNo;

    /**
     * 支付方式：1-微信，2-支付宝
     */
    @Schema(description = "支付方式：1-微信，2-支付宝")
    private Integer payWay;

    /**
     * 交易流水号
     */
    @Schema(description = "交易流水号")
    private String transactionId;

    /**
     * 支付金额
     */
    @Schema(description = "支付金额")
    private BigDecimal amount;

    /**
     * 支付内容
     */
    @Schema(description = "支付内容")
    private String content;

    /**
     * 支付状态：0-待支付，1-支付成功，2-支付失败
     */
    @Schema(description = "支付状态：0-待支付，1-支付成功，2-支付失败")
    private Integer paymentStatus;

    /**
     * 回调时间
     */
    @Schema(description = "回调时间")
    private LocalDateTime callbackTime;

    /**
     * 回调内容
     */
    @Schema(description = "回调内容")
    private String callbackContent;

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
