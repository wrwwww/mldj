package org.ml.mldj.model.payments.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 分润信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("profitsharing_info")
@Schema(name = "ProfitsharingInfo", description = "分润信息表")
public class ProfitsharingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNo;

    /**
     * 交易流水号
     */
    @Schema(description = "交易流水号")
    private String transactionId;

    /**
     * 商户订单号
     */
    @Schema(description = "商户订单号")
    private String outTradeNo;

    /**
     * 分润金额
     */
    @Schema(description = "分润金额")
    private String amount;

    /**
     * 分润状态
     */
    @Schema(description = "分润状态")
    private String state;

    /**
     * 响应内容
     */
    @Schema(description = "响应内容")
    private String responeContent;

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
