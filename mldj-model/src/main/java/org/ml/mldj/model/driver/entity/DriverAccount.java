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
 * 司机账户表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("driver_account")
@Schema(name = "DriverAccount", description = "司机账户表")
public class DriverAccount implements Serializable {

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
     * 账户总额
     */
    @Schema(description = "账户总额")
    private BigDecimal totalAmount;

    /**
     * 冻结金额
     */
    @Schema(description = "冻结金额")
    private BigDecimal lockAmount;

    /**
     * 可用金额
     */
    @Schema(description = "可用金额")
    private BigDecimal availableAmount;

    /**
     * 累计收入
     */
    @Schema(description = "累计收入")
    private BigDecimal totalIncomeAmount;

    /**
     * 累计支出
     */
    @Schema(description = "累计支出")
    private BigDecimal totalPayAmount;

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
