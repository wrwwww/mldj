package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 钱包收入表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_wallet_income", schema = "hxds_dr")
public class TbWalletIncome {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * uuid字符串
     */
    @Size(max = 32)
    @NotNull
    @Column(name = "uuid", nullable = false, length = 32)
    private String uuid;

    /**
     * 司机ID
     */
    @NotNull
    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    /**
     * 金额
     */
    @NotNull
    @Column(name = "amount", nullable = false, precision = 7, scale = 2)
    private BigDecimal amount;

    /**
     * 1充值，2奖励，3补贴
     */
    @NotNull
    @Column(name = "type", nullable = false)
    private Byte type;

    /**
     * 预支付订单ID
     */
    @Size(max = 200)
    @Column(name = "prepay_id", length = 200)
    private String prepayId;

    /**
     * 1未支付，2已支付，3已到账
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 备注信息
     */
    @Size(max = 200)
    @Column(name = "remark", length = 200)
    private String remark;

    /**
     * 创建时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}