package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_feedback", schema = "hxds_mis")
public class TbFeedback {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 反馈者姓名
     */
    @Size(max = 20)
    @NotNull
    @Column(name = "customer_name", nullable = false, length = 20)
    private String customerName;

    /**
     * 反馈者电话
     */
    @Size(max = 11)
    @NotNull
    @Column(name = "customer_tel", nullable = false, length = 11)
    private String customerTel;

    /**
     * 接待员ID
     */
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 反馈类型，1系统故障，2服务质量，3支付异常，4其他
     */
    @NotNull
    @Column(name = "type", nullable = false)
    private Byte type;

    /**
     * 反馈内容
     */
    @Size(max = 2000)
    @NotNull
    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    /**
     * 反馈状态，1未处理，2已处理
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 处理结果
     */
    @Size(max = 200)
    @Column(name = "result", length = 200)
    private String result;

    /**
     * 创建时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}