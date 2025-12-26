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
 * 司机钱包付款表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_wallet_payment", schema = "hxds_dr")
public class TbWalletPayment {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @NotNull
    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    /**
     * 支付金额
     */
    @NotNull
    @Column(name = "amount", nullable = false, precision = 7, scale = 2)
    private BigDecimal amount;

    /**
     * 1话费，2罚款，3抽奖，4缴费，5其他
     */
    @NotNull
    @Column(name = "type", nullable = false)
    private Byte type;

    /**
     * 备注
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