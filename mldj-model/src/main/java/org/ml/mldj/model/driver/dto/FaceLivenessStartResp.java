package org.ml.mldj.model.driver.dto;

import lombok.Data;

import java.util.List;

@Data
public class FaceLivenessStartResp {
    private String bizToken;
    private List<String> actions;
}
