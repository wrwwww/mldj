package org.ml.mldj.model.common;

import lombok.Data;

@Data
public class AbortUploadReq {
    private String objectKey;
    private String uploadId;
}
