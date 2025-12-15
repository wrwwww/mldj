package org.ml.mldj.system.controller;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.CompletedMultipartUploadForm;
import org.ml.mldj.model.dto.CreatePresignedRequestForm;
import org.ml.mldj.model.dto.UploadFileForm;
import org.ml.mldj.model.entity.UploadChunk;
import org.ml.mldj.model.vo.UploadInitVO;
import org.ml.mldj.system.mapper.UploadChunkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.internal.signing.DefaultS3Presigner;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController("/upload")
public class UploadController {


    @Autowired
    S3Client s3Client;
    @Value("${rustfs.s3.bucket}")
    String bucketName;
    @Value("rustfs.s3.chunkSize")
    int chunkSize;
    @Autowired
    UploadChunkMapper uploadChunkMapper;
    private Mapper mapper;

    String objectKey(UploadFileForm uploadFileForm) {
        return "";
    }

    @GetMapping("/upload/init")
    public Result<UploadInitVO> uploadInit(@RequestBody UploadFileForm form) {
        String objectKey = objectKey(form);
        CreateMultipartUploadRequest build = CreateMultipartUploadRequest.builder().bucket(bucketName).key(objectKey).build();
        CreateMultipartUploadResponse multipartUpload = s3Client.createMultipartUpload(build);
        String uploadId = multipartUpload.uploadId();
        // 使用文件大小配合chunkSize设置前端上传分块
        int chunkCount = form.getFileSize() % chunkSize == 0 ? form.getFileSize() / chunkSize : form.getFileSize() / chunkSize + 1;

        UploadInitVO uploadInitVO = new UploadInitVO();
        uploadInitVO.setObjectKey(objectKey);
        uploadInitVO.setUploadId(uploadId);
        uploadInitVO.setChunkCount(String.valueOf(chunkCount));
        // dao 存文件的信息
        List<UploadChunk> list = new ArrayList<>();
        for (int i = 0; i < chunkCount; i++) {
            UploadChunk uploadChunk = new UploadChunk();
            uploadChunk.setFileMd5(objectKey);
            uploadChunk.setFileSize(String.valueOf(form.getFileSize()));
            uploadChunk.setFileName(form.getFileName());
            uploadChunk.setUploadStatus("0");
            uploadChunk.setTotalChunk(String.valueOf(i));
            list.add(uploadChunk);
        }
        uploadChunkMapper.insertOrUpdate(list);

        return Result.success(uploadInitVO);
    }

    /**
     * @return
     */
    @GetMapping("/upload")
    public Result<String> createPresignedRequest(CreatePresignedRequestForm form) {
        // 上传分片信息获取临时put url

        S3Presigner s3Presigner = S3Presigner.create();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(form.getObjectKey())
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(b -> b
                .putObjectRequest(objectRequest)
                .signatureDuration(Duration.ofMinutes(10)));
        String url = presignedRequest.url().toString();
        s3Presigner.close();
        return Result.success(url);
    }

    /**
     * 上传进度
     *
     * @return
     */
    public Result<?> load(String fileMd5) {
        List<UploadChunk> uploadChunks = uploadChunkMapper.listByFileMd5(fileMd5);
        List<UploadChunk> list = uploadChunks.stream().filter(uploadChunk -> Objects.equals(uploadChunk.getUploadStatus(), "1")).toList();

        return Result.success();
    }

    @GetMapping("/xx/{objectKey}/{chunkNum}")
    public Result<?> x(@PathVariable("chunkNum") int chunkNum, @PathVariable("objectKey") String objectKey) {
        // 通知分片上传完成
        // 设置分片号为chunkNum objectKey的更新上传状态
        uploadChunkMapper.chunkUploadSuccess(objectKey, chunkNum);
        return Result.success();
    }

    /**
     * 整合分片接口
     */
    @GetMapping("/completeMultipartUpload")
    public Result<Boolean> completeMultipartUpload(CompletedMultipartUploadForm form) {
        List<CompletedPart> parts = form.getUploadedParts().stream()
                .map(p -> CompletedPart.builder()
                        .partNumber(p.getPartNumber())
                        .eTag(p.getETag())
                        .build())
                .collect(Collectors.toList());

        CompletedMultipartUpload completedUpload = CompletedMultipartUpload.builder()
                .parts(parts)
                .build();

        CompleteMultipartUploadRequest completeRequest = CompleteMultipartUploadRequest.builder()
                .bucket(bucketName)
                .key(form.getObjectKey())
                .uploadId(form.getUploadId())
                .multipartUpload(completedUpload)
                .build();

        s3Client.completeMultipartUpload(completeRequest);
        return Result.success(true);
    }

}
