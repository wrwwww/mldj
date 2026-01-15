package org.ml.mldj.model.driver.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FaceLivenessResultReq {
    private String liveFaceImageUrl;
    private BigDecimal livenessScore;
    private BigDecimal faceSimilarity;
    private String traceId;
}
