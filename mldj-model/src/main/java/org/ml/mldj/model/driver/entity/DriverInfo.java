package org.ml.mldj.model.driver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机信息表
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Getter
@Setter
@ToString
@TableName("driver_info")
@Schema(name = "DriverInfo", description = "司机信息表")
public class DriverInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信OpenID
     */
    @Schema(description = "微信OpenID")
    private String wxOpenId;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 头像URL
     */
    @Schema(description = "头像URL")
    private String avatarUrl;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名")
    private String name;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String gender;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDateTime birthday;

    /**
     * 身份证号码
     */
    @Schema(description = "身份证号码")
    private String idcardNo;

    /**
     * 身份证地址
     */
    @Schema(description = "身份证地址")
    private String idcardAddress;

    /**
     * 身份证有效期
     */
    @Schema(description = "身份证有效期")
    private LocalDateTime idcardExpire;

    /**
     * 身份证正面照片
     */
    @Schema(description = "身份证正面照片")
    private String idcardFrontUrl;

    /**
     * 身份证背面照片
     */
    @Schema(description = "身份证背面照片")
    private String idcardBackUrl;

    /**
     * 手持身份证照片
     */
    @Schema(description = "手持身份证照片")
    private String idcardHandUrl;

    /**
     * 驾驶证准驾车型
     */
    @Schema(description = "驾驶证准驾车型")
    private String driverLicenseClass;

    /**
     * 驾驶证号码
     */
    @Schema(description = "驾驶证号码")
    private String driverLicenseNo;

    /**
     * 驾驶证有效期
     */
    @Schema(description = "驾驶证有效期")
    private LocalDateTime driverLicenseExpire;

    /**
     * 驾驶证初次领证日期
     */
    @Schema(description = "驾驶证初次领证日期")
    private LocalDateTime driverLicenseIssueDate;

    /**
     * 驾驶证正面照片
     */
    @Schema(description = "驾驶证正面照片")
    private String driverLicenseFrontUrl;

    /**
     * 驾驶证背面照片
     */
    @Schema(description = "驾驶证背面照片")
    private String driverLicenseBackUrl;

    /**
     * 手持驾驶证照片
     */
    @Schema(description = "手持驾驶证照片")
    private String driverLicenseHandUrl;

    /**
     * 紧急联系人姓名
     */
    @Schema(description = "紧急联系人姓名")
    private String contactName;

    /**
     * 紧急联系人电话
     */
    @Schema(description = "紧急联系人电话")
    private String contactPhone;

    /**
     * 紧急联系人关系
     */
    @Schema(description = "紧急联系人关系")
    private String contactRelationship;

    /**
     * 人脸模型ID
     */
    @Schema(description = "人脸模型ID")
    private String faceModelId;

    /**
     * 工号
     */
    @Schema(description = "工号")
    private String jobNo;

    /**
     * 接单数量
     */
    @Schema(description = "接单数量")
    private Integer orderCount;

    /**
     * 评分
     */
    @Schema(description = "评分")
    private BigDecimal score;

    /**
     * 认证状态：0-未认证，1-已认证
     */
    @Schema(description = "认证状态：0-未认证，1-已认证")
    private Integer authStatus;

    /**
     * 状态：0-禁用，1-正常
     */
    @Schema(description = "状态：0-禁用，1-正常")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除标志 0=未删除 1=已删除
     */
    @Schema(description = "逻辑删除标志 0=未删除 1=已删除")
    private Boolean isDeleted;
}
