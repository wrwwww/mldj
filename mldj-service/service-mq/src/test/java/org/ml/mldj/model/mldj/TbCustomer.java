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

/**
 * 客户表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_customer", schema = "hxds_cst")
public class TbCustomer {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 小程序授权字符串
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "open_id", nullable = false, length = 200)
    private String openId;

    /**
     * 客户昵称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 200)
    private String nickname;

    /**
     * 性别
     */
    @NotNull
    @Column(name = "sex", nullable = false)
    private Character sex;

    /**
     * 头像
     */
    @Size(max = 200)
    @Column(name = "photo", length = 200)
    private String photo;

    /**
     * 电话
     */
    @Size(max = 11)
    @NotNull
    @Column(name = "tel", nullable = false, length = 11)
    private String tel;

    /**
     * 邮箱
     */
    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    /**
     * 1有效，2禁用
     */
    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    /**
     * 注册时间
     */
    @NotNull
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

}