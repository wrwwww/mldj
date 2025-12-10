package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 司机禁闭表（禁止接单）
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_driver_lockdown")
@Schema(name = "DriverLockdown", description = "司机禁闭表（禁止接单）")
public class DriverLockdown implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 原因
     */
    @Schema(description = "原因")
    private String reason;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    private Long orderId;

    /**
     * 起始日期
     */
    @Schema(description = "起始日期")
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @Schema(description = "结束日期")
    private LocalDate endDate;

    /**
     * 天数
     */
    @Schema(description = "天数")
    private Short days;
}
