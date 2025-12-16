package org.ml.mldj.model.vo;

import lombok.Data;

@Data
public class UploadInitVO {
    String uploadId;
    String objectKey;
    String chunkCount;
    long chunkSize;
}
