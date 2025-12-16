package org.ml.mldj.system.controller;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.CompletedMultipartUploadForm;
import org.ml.mldj.model.dto.CreatePresignedRequestForm;
import org.ml.mldj.model.dto.UploadFileForm;
import org.ml.mldj.model.entity.UploadChunk;
import org.ml.mldj.model.vo.UploadInitVO;
import org.ml.mldj.system.mapper.UploadChunkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController("")
@Slf4j
public class UploadController {


    @Autowired
    S3Client s3Client;
    @Value("${rustfs.s3.bucket}")
    String bucketName;
    @Value("${rustfs.s3.chunk-size}")
    DataSize chunkSize;
    @Autowired
    UploadChunkMapper uploadChunkMapper;
    @Autowired
    S3Presigner s3Presigner;

    String objectKey(UploadFileForm uploadFileForm) {
        return uploadFileForm.getFileMd5();
    }

    @PostMapping("/upload/init")
    public Result<UploadInitVO> uploadInit(@RequestBody UploadFileForm form) {
        String objectKey = objectKey(form);
        CreateMultipartUploadRequest build = CreateMultipartUploadRequest.builder().bucket(bucketName).key(objectKey).build();
        CreateMultipartUploadResponse multipartUpload = s3Client.createMultipartUpload(build);
        String uploadId = multipartUpload.uploadId();
        // 使用文件大小配合chunkSize设置前端上传分块
        long chunkCount = form.getFileSize() % chunkSize.toBytes() == 0 ? form.getFileSize() / chunkSize.toBytes() : form.getFileSize() / chunkSize.toBytes() + 1;

        UploadInitVO uploadInitVO = new UploadInitVO();
        uploadInitVO.setObjectKey(objectKey);
        uploadInitVO.setUploadId(uploadId);
        uploadInitVO.setChunkCount(String.valueOf(chunkCount));
        uploadInitVO.setChunkSize(chunkSize.toBytes());
        // dao 存文件的信息
        List<UploadChunk> list = getUploadChunks(form, chunkCount, objectKey);
        uploadChunkMapper.insertOrUpdate(list);

        return Result.success(uploadInitVO);
    }

    private List<UploadChunk> getUploadChunks(UploadFileForm form, long chunkCount, String objectKey) {
        List<UploadChunk> list = new ArrayList<>();
        for (int i = 0; i < chunkCount; i++) {
            UploadChunk uploadChunk = new UploadChunk();
            uploadChunk.setFileMd5(objectKey);
            uploadChunk.setFileSize(String.valueOf(form.getFileSize()));
            uploadChunk.setFileName(form.getFileName());
            uploadChunk.setChunkSize(String.valueOf(chunkSize.toBytes()));
            uploadChunk.setUploadStatus("0");
            uploadChunk.setPartNumber(String.valueOf(i));
            uploadChunk.setTotalChunk(String.valueOf(chunkCount));
            uploadChunk.setFileExt(form.getFileExtension());
            list.add(uploadChunk);
        }
        return list;
    }

    /**
     * 获取分片签名的url
     * @return
     */
    @GetMapping("/upload/presign")
    public Result<String> createPresignedRequest(@ModelAttribute CreatePresignedRequestForm form) {
        // 上传分片信息获取临时put url
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(bucketName)
                .key(form.getObjectKey())
                .uploadId(form.getUploadId())     // 必须
                .partNumber(form.getPartNumber()) // 必须，每片不同
                .build();
        PresignedUploadPartRequest presignedRequest = s3Presigner.presignUploadPart(b -> b
                .uploadPartRequest(uploadPartRequest)
                .signatureDuration(Duration.ofMinutes(10)));
        String url = presignedRequest.url().toString();
        s3Presigner.close();
        return Result.success(url);
    }

    /**
     * 上传进度
     *  返回已经上传成功的分片
     * @return
     */
    @GetMapping("/upload/uploaded-chunks")
    public Result<?> load(@RequestParam("fileMd5") String fileMd5) {
        List<UploadChunk> uploadChunks = uploadChunkMapper.listByFileMd5(fileMd5);
        List<UploadChunk> list = uploadChunks.stream().filter(uploadChunk -> Objects.equals(uploadChunk.getUploadStatus(), "1")).toList();

        return Result.success(list);
    }

    @PutMapping("/upload/{objectKey}/{partNumber}")
    public Result<?> x(@PathVariable("partNumber") int partNumber, @PathVariable("objectKey") String objectKey) {
        // 通知分片上传完成
        // 设置分片号为chunkNum objectKey的更新上传状态
        uploadChunkMapper.chunkUploadSuccess(objectKey, partNumber);
        return Result.success();
    }

    /**
     * 整合分片接口
     */
    @PostMapping("/upload/complete")
    public Result<Boolean> completeMultipartUpload(@RequestBody CompletedMultipartUploadForm form) {
        List<CompletedPart> parts = form.getUploadedChunks().stream()
                .map(p -> CompletedPart.builder()
                        .partNumber(p.getPartNumber())
                        .eTag(p.getTag())
                        .build()).sorted(Comparator.comparingInt(CompletedPart::partNumber)).collect(Collectors.toList());

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
