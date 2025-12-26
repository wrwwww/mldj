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
 * 订单评价表
 */
@Getter
@Setter
@Entity
@Table(name = "order_comment", schema = "mailang")
public class OrderComment {
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
     * 评分：1-5星
     */
    @Column(name = "rate")
    private Integer rate;

    /**
     * 评价内容
     */
    @Size(max = 500)
    @Column(name = "remark", length = 500)
    private String remark;

    /**
     * 状态：0-待审核，1-已审核，2-已屏蔽
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 审核实例ID
     */
    @Size(max = 100)
    @Column(name = "instance_id", length = 100)
    private String instanceId;

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