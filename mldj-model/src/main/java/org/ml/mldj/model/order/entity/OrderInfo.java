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
 * 订单信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("order_info")
@Schema(name = "OrderInfo", description = "订单信息表")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    private Long customerId;

    /**
     * 订单编号
     */
    @Schema(description = "订单编号")
    private String orderNo;

    /**
     * 起点位置
     */
    @Schema(description = "起点位置")
    private String startLocation;

    /**
     * 起点经度
     */
    @Schema(description = "起点经度")
    private BigDecimal startPointLongitude;

    /**
     * 起点纬度
     */
    @Schema(description = "起点纬度")
    private BigDecimal startPointLatitude;

    /**
     * 终点位置
     */
    @Schema(description = "终点位置")
    private String endLocation;

    /**
     * 终点经度
     */
    @Schema(description = "终点经度")
    private BigDecimal endPointLongitude;

    /**
     * 终点纬度
     */
    @Schema(description = "终点纬度")
    private BigDecimal endPointLatitude;

    /**
     * 预计里程（公里）
     */
    @Schema(description = "预计里程（公里）")
    private BigDecimal expectDistance;

    /**
     * 实际里程（公里）
     */
    @Schema(description = "实际里程（公里）")
    private BigDecimal realDistance;

    /**
     * 预计金额
     */
    @Schema(description = "预计金额")
    private BigDecimal expectAmount;

    /**
     * 实际金额
     */
    @Schema(description = "实际金额")
    private BigDecimal realAmount;

    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal favourFee;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 接单时间
     */
    @Schema(description = "接单时间")
    private LocalDateTime acceptTime;

    /**
     * 到达时间
     */
    @Schema(description = "到达时间")
    private LocalDateTime arriveTime;

    /**
     * 开始服务时间
     */
    @Schema(description = "开始服务时间")
    private LocalDateTime startServiceTime;

    /**
     * 结束服务时间
     */
    @Schema(description = "结束服务时间")
    private LocalDateTime endServiceTime;

    /**
     * 支付时间
     */
    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    /**
     * 取消规则ID
     */
    @Schema(description = "取消规则ID")
    private Long cancelRuleId;

    /**
     * 车牌号
     */
    @Schema(description = "车牌号")
    private String carLicense;

    /**
     * 车型
     */
    @Schema(description = "车型")
    private String carType;

    /**
     * 车前照片
     */
    @Schema(description = "车前照片")
    private String carFrontUrl;

    /**
     * 车后照片
     */
    @Schema(description = "车后照片")
    private String carBackUrl;

    /**
     * 交易流水号
     */
    @Schema(description = "交易流水号")
    private String transactionId;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

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
