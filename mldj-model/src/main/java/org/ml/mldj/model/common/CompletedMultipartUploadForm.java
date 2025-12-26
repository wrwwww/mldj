package org.ml.mldj.model.common;

import lombok.Data;

import java.util.List;

@Data
public class CompletedMultipartUploadForm {
    String uploadId;
    List<Part> uploadedChunks;
    String objectKey;
}
