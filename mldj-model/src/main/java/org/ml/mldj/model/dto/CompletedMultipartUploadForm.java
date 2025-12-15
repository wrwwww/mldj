package org.ml.mldj.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompletedMultipartUploadForm {
    String uploadId;
    List<Part> uploadedParts;
    String objectKey;
    @Data
   public class Part {
        int partNumber;
        String ETag;
    }
}
