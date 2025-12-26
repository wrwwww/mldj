package org.ml.mldj.model.common;

import lombok.Data;

@Data
public class CreatePresignedRequestForm {
    String uploadId;
    int partNumber;
    String objectKey;

}
