package org.ml.mldj.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadedPartVO {
    private Integer partNumber;
    private String eTag;
}
