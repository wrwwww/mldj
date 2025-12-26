package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 用户优惠券表
 */
@Getter
@Setter
@Entity
@Table(name = "customer_coupon", schema = "mailang")
public class CustomerCoupon {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 优惠券ID
     */
    @Column(name = "coupon_id")
    private Long couponId;

    /**
     * 客户ID
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 状态：0-未使用，1-已使用，2-已过期
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 领取时间
     */
    @Column(name = "receive_time")
    private Instant receiveTime;

    /**
     * 使用时间
     */
    @Column(name = "used_time")
    private Instant usedTime;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 过期时间
     */
    @Column(name = "expire_time")
    private Instant expireTime;

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