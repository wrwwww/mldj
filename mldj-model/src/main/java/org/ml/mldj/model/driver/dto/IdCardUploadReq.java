package org.ml.mldj.model.driver.dto;

import lombok.Data;

@Data
public class IdCardUploadReq {
    private String frontImageUrl;
    private String backImageUrl;
}
