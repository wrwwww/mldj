package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 分润信息表
 */
@Getter
@Setter
@Entity
@Table(name = "profitsharing_info", schema = "mailang")
public class ProfitsharingInfo {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 司机ID
     */
    @Column(name = "driver_id")
    private Long driverId;

    /**
     * 订单编号
     */
    @Size(max = 100)
    @Column(name = "order_no", length = 100)
    private String orderNo;

    /**
     * 交易流水号
     */
    @Size(max = 100)
    @Column(name = "transaction_id", length = 100)
    private String transactionId;

    /**
     * 商户订单号
     */
    @Size(max = 100)
    @Column(name = "out_trade_no", length = 100)
    private String outTradeNo;

    /**
     * 分润金额
     */
    @Size(max = 50)
    @Column(name = "amount", length = 50)
    private String amount;

    /**
     * 分润状态
     */
    @Size(max = 50)
    @Column(name = "state", length = 50)
    private String state;

    /**
     * 响应内容
     */
    @Lob
    @Column(name = "respone_content")
    private String responeContent;

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