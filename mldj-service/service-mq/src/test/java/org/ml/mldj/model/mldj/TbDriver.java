package org.ml.mldj.model.mldj;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

/**
 * 代驾司机表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_driver", schema = "hxds_dr")
public class TbDriver {
    /**
     * 主键
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 小程序长期授权
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "open_id", nullable = false, length = 200)
    private String openId;

    /**
     * 昵称
     */
    @Size(max = 200)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 200)
    private String nickname;

    /**
     * 姓名
     */
    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Character sex;

    /**
     * 头像
     */
    @Size(max = 200)
    @Column(name = "photo", length = 200)
    private String photo;

    /**
     * 身份证号码
     */
    @Size(max = 18)
    @Column(name = "pid", length = 18)
    private String pid;

    /**
     * 生日
     */
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * 电话
     */
    @Size(max = 11)
    @Column(name = "tel", length = 11)
    private String tel;

    /**
     * 邮箱
     */
    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    /**
     * 收信地址
     */
    @Size(max = 200)
    @Column(name = "mail_address", length = 200)
    private String mailAddress;

    /**
     * 应急联系人
     */
    @Size(max = 20)
    @Column(name = "contact_name", length = 20)
    private String contactName;

    /**
     * 应急联系人电话
     */
    @Size(max = 11)
    @Column(name = "contact_tel", length = 11)
    private String contactTel;

    /**
     * 1未认证，2已认证，3审核中
     */
    @Column(name = "real_auth")
    private Byte realAuth;

    /**
     * 身份证地址
     */
    @Size(max = 200)
    @Column(name = "idcard_address", length = 200)
    private String idcardAddress;

    /**
     * 身份证有效期
     */
    @Column(name = "idcard_expiration")
    private LocalDate idcardExpiration;

    /**
     * 身份证正面
     */
    @Size(max = 200)
    @Column(name = "idcard_front", length = 200)
    private String idcardFront;

    /**
     * 身份证背面
     */
    @Size(max = 200)
    @Column(name = "idcard_back", length = 200)
    private String idcardBack;

    /**
     * 手持身份证
     */
    @Size(max = 200)
    @Column(name = "idcard_holding", length = 200)
    private String idcardHolding;

    /**
     * 驾驶证类型
     */
    @Size(max = 20)
    @Column(name = "drcard_type", length = 20)
    private String drcardType;

    /**
     * 驾驶证有效期
     */
    @Column(name = "drcard_expiration")
    private LocalDate drcardExpiration;

    /**
     * 驾驶证初次领证日期
     */
    @Column(name = "drcard_issue_date")
    private LocalDate drcardIssueDate;

    /**
     * 驾驶证正面
     */
    @Size(max = 200)
    @Column(name = "drcard_front", length = 200)
    private String drcardFront;

    /**
     * 驾驶证背面
     */
    @Size(max = 200)
    @Column(name = "drcard_back", length = 200)
    private String drcardBack;

    /**
     * 手持驾驶证
     */
    @Size(max = 200)
    @Column(name = "drcard_holding", length = 200)
    private String drcardHolding;

    /**
     * 家庭地址，包含地址和定位，用于回家导航
     */
    @Column(name = "home")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> home;

    /**
     * 摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量
     */
    @Column(name = "summary")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> summary;

    /**
     * 是否在腾讯云归档存放司机面部信息
     */
    @NotNull
    @Column(name = "archive", nullable = false)
    private Boolean archive = false;

    /**
     * 状态，1正常，2禁用，3.降低接单量
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