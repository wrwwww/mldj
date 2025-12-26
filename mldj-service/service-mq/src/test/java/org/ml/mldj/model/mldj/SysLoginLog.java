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
 * 系统登录日志表
 */
@Getter
@Setter
@Entity
@Table(name = "sys_login_log", schema = "mailang")
public class SysLoginLog {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户名
     */
    @Size(max = 100)
    @Column(name = "username", length = 100)
    private String username;

    /**
     * 登录IP地址
     */
    @Size(max = 64)
    @Column(name = "ipaddr", length = 64)
    private String ipaddr;

    /**
     * 登录状态：0-失败，1-成功
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 登录消息
     */
    @Size(max = 255)
    @Column(name = "msg")
    private String msg;

    /**
     * 访问时间
     */
    @Column(name = "access_time")
    private Instant accessTime;

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