package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 司机账户表
 */
@Getter
@Setter
@Entity
@Table(name = "driver_account", schema = "mailang")
public class DriverAccount {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 账户总额
     */
    @Column(name = "total_amount", precision = 18, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 冻结金额
     */
    @Column(name = "lock_amount", precision = 18, scale = 2)
    private BigDecimal lockAmount;

    /**
     * 可用金额
     */
    @Column(name = "available_amount", precision = 18, scale = 2)
    private BigDecimal availableAmount;

    /**
     * 累计收入
     */
    @Column(name = "total_income_amount", precision = 18, scale = 2)
    private BigDecimal totalIncomeAmount;

    /**
     * 累计支出
     */
    @Column(name = "total_pay_amount", precision = 18, scale = 2)
    private BigDecimal totalPayAmount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Instant createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

}