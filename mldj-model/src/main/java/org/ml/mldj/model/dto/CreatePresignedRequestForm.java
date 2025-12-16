package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class CreatePresignedRequestForm {
    String uploadId;
    int partNumber;
    String objectKey;

}
