package org.ml.mldj.model.dto;

import lombok.Data;

@Data
public class UploadFileForm {
    String fileMd5;
    String fileName;
    String fileExtension;
    int fileSize;
}
