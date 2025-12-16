package org.ml.mldj.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("upload_chunk")
@Data
public class UploadChunk {
    @TableId(type = IdType.ASSIGN_ID)
    String id;
    String fileMd5;
    String chunkSize;
    //    当前分片数
    String partNumber;
    String fileSize;
    // 总的分片数量
    String totalChunk;
    String uploadStatus;
    String fileName;
    String fileExt;

}
