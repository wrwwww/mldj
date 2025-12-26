package org.ml.mldj.model.customer.entity;

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
 * 客户车辆信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("customer_car")
@Schema(name = "CustomerCar", description = "客户车辆信息表")
public class CustomerCar implements Serializable {

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
     * 车牌号
     */
    @Schema(description = "车牌号")
    private String license;

    /**
     * 车辆品牌
     */
    @Schema(description = "车辆品牌")
    private String brand;

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
