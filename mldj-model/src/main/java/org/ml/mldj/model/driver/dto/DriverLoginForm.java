package org.ml.mldj.model.driver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//@Entity
@Getter
@Setter
@AllArgsConstructor
public class DriverLoginForm {
    private String code;
    /**
     * 主键
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 小程序长期授权
     */
    @Schema(description = "小程序长期授权")
    private String openId;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

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
     * 身份证号码
     */
    @Schema(description = "身份证号码")
    private String pid;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDate birthday;

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
     * 收信地址
     */
    @Schema(description = "收信地址")
    private String mailAddress;

    /**
     * 应急联系人
     */
    @Schema(description = "应急联系人")
    private String contactName;

    /**
     * 应急联系人电话
     */
    @Schema(description = "应急联系人电话")
    private String contactTel;

    /**
     * 1未认证，2已认证，3审核中
     */
    @Schema(description = "1未认证，2已认证，3审核中")
    private Byte realAuth;

    /**
     * 身份证地址
     */
    @Schema(description = "身份证地址")
    private String idcardAddress;

    /**
     * 身份证有效期
     */
    @Schema(description = "身份证有效期")
    private LocalDate idcardExpiration;

    /**
     * 身份证正面
     */
    @Schema(description = "身份证正面")
    private String idcardFront;

    /**
     * 身份证背面
     */
    @Schema(description = "身份证背面")
    private String idcardBack;

    /**
     * 手持身份证
     */
    @Schema(description = "手持身份证")
    private String idcardHolding;

    /**
     * 驾驶证类型
     */
    @Schema(description = "驾驶证类型")
    private String drcardType;

    /**
     * 驾驶证有效期
     */
    @Schema(description = "驾驶证有效期")
    private LocalDate drcardExpiration;

    /**
     * 驾驶证初次领证日期
     */
    @Schema(description = "驾驶证初次领证日期")
    private LocalDate drcardIssueDate;

    /**
     * 驾驶证正面
     */
    @Schema(description = "驾驶证正面")
    private String drcardFront;

    /**
     * 驾驶证背面
     */
    @Schema(description = "驾驶证背面")
    private String drcardBack;

    /**
     * 手持驾驶证
     */
    @Schema(description = "手持驾驶证")
    private String drcardHolding;

    /**
     * 家庭地址，包含地址和定位，用于回家导航
     */
    @Schema(description = "家庭地址，包含地址和定位，用于回家导航")
    private String home;

    /**
     * 摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量
     */
    @Schema(description = "摘要信息，level等级，totalOrder接单数，weekOrder周接单，weekComment周好评，appeal正在申诉量")
    private String summary;

    /**
     * 是否在腾讯云归档存放司机面部信息
     */
    @Schema(description = "是否在腾讯云归档存放司机面部信息")
    private Boolean archive;

    /**
     * 状态，1正常，2禁用，3.降低接单量
     */
    @Schema(description = "状态，1正常，2禁用，3.降低接单量")
    private Byte status;


}
