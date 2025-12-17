package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class AbortUploadReq {
    private String objectKey;
    private String uploadId;
}
