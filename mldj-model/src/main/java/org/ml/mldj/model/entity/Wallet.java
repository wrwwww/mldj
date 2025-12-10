package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 钱包表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_wallet")
@Schema(name = "Wallet", description = "钱包表")
public class Wallet implements Serializable {

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
     * 钱包金额
     */
    @Schema(description = "钱包金额")
    private BigDecimal balance;

    /**
     * 支付密码，如果为空，不能支付，提示用户设置支付密码
     */
    @Schema(description = "支付密码，如果为空，不能支付，提示用户设置支付密码")
    private String password;
}
