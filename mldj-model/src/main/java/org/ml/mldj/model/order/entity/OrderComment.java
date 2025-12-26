package org.ml.mldj.model.order.entity;

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
 * 订单评价表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("order_comment")
@Schema(name = "OrderComment", description = "订单评价表")
public class OrderComment implements Serializable {

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
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    private Long customerId;

    /**
     * 评分：1-5星
     */
    @Schema(description = "评分：1-5星")
    private Integer rate;

    /**
     * 评价内容
     */
    @Schema(description = "评价内容")
    private String remark;

    /**
     * 状态：0-待审核，1-已审核，2-已屏蔽
     */
    @Schema(description = "状态：0-待审核，1-已审核，2-已屏蔽")
    private Integer status;

    /**
     * 审核实例ID
     */
    @Schema(description = "审核实例ID")
    private String instanceId;

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
