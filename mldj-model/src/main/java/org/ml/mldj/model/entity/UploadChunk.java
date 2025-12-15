package org.ml.mldj.model.entity;

import lombok.Data;

@Data
public class UploadChunk {
    String id;
    String fileMd5;
    String chunkSize;
    //    当前分片数
    String chunkNum;
    String fileSize;
    // 总的分片数量
    String totalChunk;
    String uploadStatus;
    String fileName;
    String fileExt;

}
