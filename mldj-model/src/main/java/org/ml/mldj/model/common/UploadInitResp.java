package org.ml.mldj.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadInitResp {
    private String uploadId;
    private String objectKey;
    private Long chunkSize;
    private Integer chunkCount;
    private Boolean instant;
}
