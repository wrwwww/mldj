package org.ml.mldj.model.driver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RealnameSubmitDTO {

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
}
