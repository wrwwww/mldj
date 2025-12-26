package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 支付信息表
 */
@Getter
@Setter
@Entity
@Table(name = "payment_info", schema = "mailang")
public class PaymentInfo {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 客户OpenID
     */
    @Size(max = 100)
    @Column(name = "customer_open_id", length = 100)
    private String customerOpenId;

    /**
     * 司机OpenID
     */
    @Size(max = 100)
    @Column(name = "driver_open_id", length = 100)
    private String driverOpenId;

    /**
     * 订单编号
     */
    @Size(max = 100)
    @Column(name = "order_no", length = 100)
    private String orderNo;

    /**
     * 支付方式：1-微信，2-支付宝
     */
    @Column(name = "pay_way")
    private Integer payWay;

    /**
     * 交易流水号
     */
    @Size(max = 100)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    /**
     * 支付金额
     */
    @Column(name = "amount", precision = 18, scale = 2)
    private BigDecimal amount;

    /**
     * 支付内容
     */
    @Size(max = 255)
    @Column(name = "content")
    private String content;

    /**
     * 支付状态：0-待支付，1-支付成功，2-支付失败
     */
    @Column(name = "payment_status")
    private Integer paymentStatus;

    /**
     * 回调时间
     */
    @Column(name = "callback_time")
    private Instant callbackTime;

    /**
     * 回调内容
     */
    @Lob
    @Column(name = "callback_content")
    private String callbackContent;

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