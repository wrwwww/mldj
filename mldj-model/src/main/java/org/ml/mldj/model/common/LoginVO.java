package org.ml.mldj.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class LoginVO {
    private String token;
    private String refreshToken;
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

}
