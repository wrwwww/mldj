package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class UploadInitReq {
    private String fileName;
    private String fileKey;
    private Long fileSize;
}
