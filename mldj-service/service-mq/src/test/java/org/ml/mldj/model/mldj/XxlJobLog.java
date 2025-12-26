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
 * XXL-JOB任务日志表
 */
@Getter
@Setter
@Entity
@Table(name = "xxl_job_log", schema = "mailang")
public class XxlJobLog {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 任务ID
     */
    @Column(name = "job_id")
    private Long jobId;

    /**
     * 执行状态：0-失败，1-成功
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 错误信息
     */
    @Size(max = 1000)
    @Column(name = "error", length = 1000)
    private String error;

    /**
     * 执行耗时（毫秒）
     */
    @Column(name = "times")
    private Integer times;

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