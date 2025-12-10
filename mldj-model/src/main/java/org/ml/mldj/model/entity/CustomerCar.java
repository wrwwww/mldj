package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 客户车辆表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_customer_car")
@Schema(name = "CustomerCar", description = "客户车辆表")
public class CustomerCar implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    private Long customerId;

    /**
     * 车牌号
     */
    @Schema(description = "车牌号")
    private String carPlate;

    /**
     * 车型
     */
    @Schema(description = "车型")
    private String carType;
}
