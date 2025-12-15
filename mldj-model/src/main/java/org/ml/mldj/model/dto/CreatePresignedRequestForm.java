package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class CreatePresignedRequestForm {
    String upload;
    String partNumber;
    String objectKey;

}
