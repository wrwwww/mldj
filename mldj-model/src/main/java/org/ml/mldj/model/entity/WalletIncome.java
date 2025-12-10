package org.ml.mldj.model.entity;

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
 * 钱包收入表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_wallet_income")
@Schema(name = "WalletIncome", description = "钱包收入表")
public class WalletIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * uuid字符串
     */
    @Schema(description = "uuid字符串")
    private String uuid;

    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 金额
     */
    @Schema(description = "金额")
    private BigDecimal amount;

    /**
     * 1充值，2奖励，3补贴
     */
    @Schema(description = "1充值，2奖励，3补贴")
    private Byte type;

    /**
     * 预支付订单ID
     */
    @Schema(description = "预支付订单ID")
    private String prepayId;

    /**
     * 1未支付，2已支付，3已到账
     */
    @Schema(description = "1未支付，2已支付，3已到账")
    private Byte status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
