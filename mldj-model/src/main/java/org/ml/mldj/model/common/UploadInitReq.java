package org.ml.mldj.model.common;

import lombok.Data;

@Data
public class UploadInitReq {
    private String fileName;
    private String fileKey;
    private Long fileSize;
}
