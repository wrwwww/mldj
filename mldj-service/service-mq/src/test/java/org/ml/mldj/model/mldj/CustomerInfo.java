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
 * 客户信息表
 */
@Getter
@Setter
@Entity
@Table(name = "customer_info", schema = "mailang")
public class CustomerInfo {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 微信OpenID
     */
    @Size(max = 100)
    @Column(name = "wx_open_id", length = 100)
    private String wxOpenId;

    /**
     * 昵称
     */
    @Size(max = 100)
    @Column(name = "nickname", length = 100)
    private String nickname;

    /**
     * 性别
     */
    @Size(max = 10)
    @Column(name = "gender", length = 10)
    private String gender;

    /**
     * 头像URL
     */
    @Size(max = 255)
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 手机号
     */
    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    /**
     * 状态：0-禁用，1-正常
     */
    @Column(name = "status")
    private Integer status;

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