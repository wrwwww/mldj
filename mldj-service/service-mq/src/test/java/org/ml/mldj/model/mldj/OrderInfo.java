package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 订单信息表
 */
@Getter
@Setter
@Entity
@Table(name = "order_info", schema = "mailang")
public class OrderInfo {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 客户ID
     */
    @Column(name = "customer_id")
    private Long customerId;

    /**
     * 订单编号
     */
    @Size(max = 100)
    @Column(name = "order_no", length = 100)
    private String orderNo;

    /**
     * 起点位置
     */
    @Size(max = 255)
    @Column(name = "start_location")
    private String startLocation;

    /**
     * 起点经度
     */
    @Column(name = "start_point_longitude", precision = 18, scale = 6)
    private BigDecimal startPointLongitude;

    /**
     * 起点纬度
     */
    @Column(name = "start_point_latitude", precision = 18, scale = 6)
    private BigDecimal startPointLatitude;

    /**
     * 终点位置
     */
    @Size(max = 255)
    @Column(name = "end_location")
    private String endLocation;

    /**
     * 终点经度
     */
    @Column(name = "end_point_longitude", precision = 18, scale = 6)
    private BigDecimal endPointLongitude;

    /**
     * 终点纬度
     */
    @Column(name = "end_point_latitude", precision = 18, scale = 6)
    private BigDecimal endPointLatitude;

    /**
     * 预计里程（公里）
     */
    @Column(name = "expect_distance", precision = 18, scale = 2)
    private BigDecimal expectDistance;

    /**
     * 实际里程（公里）
     */
    @Column(name = "real_distance", precision = 18, scale = 2)
    private BigDecimal realDistance;

    /**
     * 预计金额
     */
    @Column(name = "expect_amount", precision = 18, scale = 2)
    private BigDecimal expectAmount;

    /**
     * 实际金额
     */
    @Column(name = "real_amount", precision = 18, scale = 2)
    private BigDecimal realAmount;

    /**
     * 优惠金额
     */
    @Column(name = "favour_fee", precision = 18, scale = 2)
    private BigDecimal favourFee;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 接单时间
     */
    @Column(name = "accept_time")
    private Instant acceptTime;

    /**
     * 到达时间
     */
    @Column(name = "arrive_time")
    private Instant arriveTime;

    /**
     * 开始服务时间
     */
    @Column(name = "start_service_time")
    private Instant startServiceTime;

    /**
     * 结束服务时间
     */
    @Column(name = "end_service_time")
    private Instant endServiceTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Instant payTime;

    /**
     * 取消规则ID
     */
    @Column(name = "cancel_rule_id")
    private Long cancelRuleId;

    /**
     * 车牌号
     */
    @Size(max = 50)
    @Column(name = "car_license", length = 50)
    private String carLicense;

    /**
     * 车型
     */
    @Size(max = 100)
    @Column(name = "car_type", length = 100)
    private String carType;

    /**
     * 车前照片
     */
    @Size(max = 255)
    @Column(name = "car_front_url")
    private String carFrontUrl;

    /**
     * 车后照片
     */
    @Size(max = 255)
    @Column(name = "car_back_url")
    private String carBackUrl;

    /**
     * 交易流水号
     */
    @Size(max = 100)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    /**
     * 订单状态
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 备注
     */
    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

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