package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 客户车辆信息表
 */
@Getter
@Setter
@Entity
@Table(name = "customer_car", schema = "mailang")
public class CustomerCar {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 客户ID
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 车牌号
     */
    @Size(max = 50)
    @Column(name = "license", length = 50)
    private String license;

    /**
     * 车辆品牌
     */
    @Size(max = 100)
    @Column(name = "brand", length = 100)
    private String brand;

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