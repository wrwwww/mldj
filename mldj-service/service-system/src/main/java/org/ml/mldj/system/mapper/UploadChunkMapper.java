package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ml.mldj.model.entity.UploadChunk;

import java.util.List;

public interface UploadChunkMapper extends BaseMapper<UploadChunk> {

    void chunkUploadSuccess(String objectKey, int chunkNum);
    List<UploadChunk> listByFileMd5(@Param("fileMd5")String fileMd5);
}