package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Getter
@Setter
@ToString
@TableName("tb_customer")
@Schema(name = "Customer", description = "客户表")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 小程序授权字符串
     */
    @Schema(description = "小程序授权字符串")
    private String openId;

    /**
     * 客户昵称
     */
    @Schema(description = "客户昵称")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String sex;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String photo;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String tel;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 1有效，2禁用
     */
    @Schema(description = "1有效，2禁用")
    private Byte status;

    /**
     * 注册时间
     */
    @Schema(description = "注册时间")
    private LocalDateTime createTime;
}
