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
 * 客户罚款表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_customer_fine")
@Schema(name = "CustomerFine", description = "客户罚款表")
public class CustomerFine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    private Long customerId;

    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    private Long orderId;

    /**
     * 罚款金额
     */
    @Schema(description = "罚款金额")
    private BigDecimal amount;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String remark;

    /**
     * 1未缴纳，2已缴纳
     */
    @Schema(description = "1未缴纳，2已缴纳")
    private Byte status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
