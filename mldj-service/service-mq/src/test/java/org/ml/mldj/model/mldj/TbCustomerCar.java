package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户车辆表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_customer_car", schema = "hxds_cst")
public class TbCustomerCar {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 客户ID
     */
    @NotNull
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    /**
     * 车牌号
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "car_plate", nullable = false, length = 20)
    private String carPlate;

    /**
     * 车型
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "car_type", nullable = false, length = 20)
    private String carType;

}