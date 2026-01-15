package org.ml.mldj.model.driver.entity;

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
 * 司机设置表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("driver_set")
@Schema(name = "DriverSet", description = "司机设置表")
public class DriverSet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private String driverId;

    /**
     * 服务状态：0-下班，1-上班
     */
    @Schema(description = "服务状态：0-下班，1-上班")
    private Integer serviceStatus;

    /**
     * 接单距离（公里）
     */
    @Schema(description = "接单距离（公里）")
    private BigDecimal orderDistance;

    /**
     * 可接单距离（公里）
     */
    @Schema(description = "可接单距离（公里）")
    private BigDecimal acceptDistance;

    /**
     * 是否自动接单：0-否，1-是
     */
    @Schema(description = "是否自动接单：0-否，1-是")
    private Integer isAutoAccept;

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
