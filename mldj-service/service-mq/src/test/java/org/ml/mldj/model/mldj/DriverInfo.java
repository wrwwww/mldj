package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 司机信息表
 */
@Getter
@Setter
@Entity
@Table(name = "driver_info", schema = "mailang")
public class DriverInfo {
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
     * 真实姓名
     */
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    /**
     * 性别
     */
    @Size(max = 10)
    @Column(name = "gender", length = 10)
    private String gender;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private Instant birthday;

    /**
     * 身份证号码
     */
    @Size(max = 30)
    @Column(name = "idcard_no", length = 30)
    private String idcardNo;

    /**
     * 身份证地址
     */
    @Size(max = 255)
    @Column(name = "idcard_address")
    private String idcardAddress;

    /**
     * 身份证有效期
     */
    @Column(name = "idcard_expire")
    private Instant idcardExpire;

    /**
     * 身份证正面照片
     */
    @Size(max = 255)
    @Column(name = "idcard_front_url")
    private String idcardFrontUrl;

    /**
     * 身份证背面照片
     */
    @Size(max = 255)
    @Column(name = "idcard_back_url")
    private String idcardBackUrl;

    /**
     * 手持身份证照片
     */
    @Size(max = 255)
    @Column(name = "idcard_hand_url")
    private String idcardHandUrl;

    /**
     * 驾驶证准驾车型
     */
    @Size(max = 20)
    @Column(name = "driver_license_class", length = 20)
    private String driverLicenseClass;

    /**
     * 驾驶证号码
     */
    @Size(max = 50)
    @Column(name = "driver_license_no", length = 50)
    private String driverLicenseNo;

    /**
     * 驾驶证有效期
     */
    @Column(name = "driver_license_expire")
    private Instant driverLicenseExpire;

    /**
     * 驾驶证初次领证日期
     */
    @Column(name = "driver_license_issue_date")
    private Instant driverLicenseIssueDate;

    /**
     * 驾驶证正面照片
     */
    @Size(max = 255)
    @Column(name = "driver_license_front_url")
    private String driverLicenseFrontUrl;

    /**
     * 驾驶证背面照片
     */
    @Size(max = 255)
    @Column(name = "driver_license_back_url")
    private String driverLicenseBackUrl;

    /**
     * 手持驾驶证照片
     */
    @Size(max = 255)
    @Column(name = "driver_license_hand_url")
    private String driverLicenseHandUrl;

    /**
     * 紧急联系人姓名
     */
    @Size(max = 100)
    @Column(name = "contact_name", length = 100)
    private String contactName;

    /**
     * 紧急联系人电话
     */
    @Size(max = 30)
    @Column(name = "contact_phone", length = 30)
    private String contactPhone;

    /**
     * 紧急联系人关系
     */
    @Size(max = 50)
    @Column(name = "contact_relationship", length = 50)
    private String contactRelationship;

    /**
     * 人脸模型ID
     */
    @Size(max = 100)
    @Column(name = "face_model_id", length = 100)
    private String faceModelId;

    /**
     * 工号
     */
    @Size(max = 50)
    @Column(name = "job_no", length = 50)
    private String jobNo;

    /**
     * 接单数量
     */
    @Column(name = "order_count")
    private Integer orderCount;

    /**
     * 评分
     */
    @Column(name = "score", precision = 18, scale = 2)
    private BigDecimal score;

    /**
     * 认证状态：0-未认证，1-已认证
     */
    @Column(name = "auth_status")
    private Integer authStatus;

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