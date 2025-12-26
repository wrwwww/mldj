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

/**
 * 钱包表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_wallet", schema = "hxds_dr")
public class TbWallet {
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
     * 钱包金额
     */
    @NotNull
    @Column(name = "balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    /**
     * 支付密码，如果为空，不能支付，提示用户设置支付密码
     */
    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

}