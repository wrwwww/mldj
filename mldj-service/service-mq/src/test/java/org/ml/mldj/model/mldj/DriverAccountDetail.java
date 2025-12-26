package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 司机账户明细表
 */
@Getter
@Setter
@Entity
@Table(name = "driver_account_detail", schema = "mailang")
public class DriverAccountDetail {
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
     * 交易内容
     */
    @Size(max = 255)
    @Column(name = "content")
    private String content;

    /**
     * 交易类型
     */
    @Size(max = 50)
    @Column(name = "trade_type", length = 50)
    private String tradeType;

    /**
     * 交易金额
     */
    @Column(name = "amount", precision = 18, scale = 2)
    private BigDecimal amount;

    /**
     * 交易流水号
     */
    @Size(max = 100)
    @Column(name = "trade_no", length = 100)
    private String tradeNo;

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