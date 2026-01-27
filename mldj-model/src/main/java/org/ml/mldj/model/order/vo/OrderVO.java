package org.ml.mldj.model.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderVO {
    String status;
    String orderNo;
    /**
     * 起点位置
     */
    @Schema(description = "起点位置")
    private String startLocation;
    /**
     * 终点位置
     */
    @Schema(description = "终点位置")
    private String endLocation;
    /**
     * 实际里程（公里）
     */
    @Schema(description = "实际里程（公里）")
    private BigDecimal realDistance;
    /**
     * 实际金额
     */
    @Schema(description = "实际金额")
    private BigDecimal realAmount;
    /**
     * 优惠金额
     */
    @Schema(description = "优惠金额")
    private BigDecimal favourFee;
    /**
     * 司机ID
     */
    @Schema(description = "司机ID")
    private Long driverId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}

