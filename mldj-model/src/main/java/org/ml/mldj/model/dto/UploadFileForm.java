package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class UploadFileForm {
    String md5;
    String fileName;
    String fileType;
    int fileSize;
}
