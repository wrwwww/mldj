package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 订单轨迹记录表
 */
@Getter
@Setter
@Entity
@Table(name = "order_track", schema = "mailang")
public class OrderTrack {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 客户ID
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 经度
     */
    @Size(max = 50)
    @Column(name = "longitude", length = 50)
    private String longitude;

    /**
     * 纬度
     */
    @Size(max = 50)
    @Column(name = "latitude", length = 50)
    private String latitude;

    /**
     * 速度（km/h）
     */
    @Size(max = 50)
    @Column(name = "speed", length = 50)
    private String speed;

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