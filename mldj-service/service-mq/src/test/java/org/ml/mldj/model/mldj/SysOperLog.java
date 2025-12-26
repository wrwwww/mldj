package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * 系统操作日志表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_oper_log", schema = "mailang")
public class SysOperLog {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 操作标题
     */
    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    /**
     * 业务类型
     */
    @Size(max = 20)
    @Column(name = "business_type", length = 20)
    private String businessType;

    /**
     * 方法名称
     */
    @Size(max = 255)
    @Column(name = "method")
    private String method;

    /**
     * 请求方式
     */
    @Size(max = 20)
    @Column(name = "request_method", length = 20)
    private String requestMethod;

    /**
     * 操作人员类型
     */
    @Size(max = 20)
    @Column(name = "operator_type", length = 20)
    private String operatorType;

    /**
     * 操作人员
     */
    @Size(max = 100)
    @Column(name = "oper_name", length = 100)
    private String operName;

    /**
     * 部门名称
     */
    @Size(max = 100)
    @Column(name = "dept_name", length = 100)
    private String deptName;

    /**
     * 请求URL
     */
    @Size(max = 255)
    @Column(name = "oper_url")
    private String operUrl;

    /**
     * 操作IP
     */
    @Size(max = 64)
    @Column(name = "oper_ip", length = 64)
    private String operIp;

    /**
     * 请求参数
     */
    @Lob
    @Column(name = "oper_param")
    private String operParam;

    /**
     * 返回结果
     */
    @Lob
    @Column(name = "json_result")
    private String jsonResult;

    /**
     * 操作状态：0-失败，1-成功
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 错误信息
     */
    @Size(max = 500)
    @Column(name = "error_msg", length = 500)
    private String errorMsg;

    /**
     * 操作时间
     */
    @Column(name = "oper_time")
    private Instant operTime;

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