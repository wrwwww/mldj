package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "coupon_info", schema = "mailang")
public class CouponInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "coupon_type")
    private Integer couponType;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "condition_amount", precision = 10, scale = 2)
    private BigDecimal conditionAmount;

    @Column(name = "publish_count")
    private Integer publishCount;

    @Column(name = "per_limit")
    private Integer perLimit;

    @Column(name = "use_count")
    private Integer useCount;

    @Column(name = "receive_count")
    private Integer receiveCount;

    @Column(name = "expire_time")
    private Instant expireTime;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}