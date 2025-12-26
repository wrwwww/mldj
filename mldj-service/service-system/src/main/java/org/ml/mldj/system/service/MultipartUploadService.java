package org.ml.mldj.system.service;

import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.model.common.UploadInitReq;
import org.ml.mldj.model.common.UploadedPartVO;
import org.ml.mldj.model.common.UploadInitResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class MultipartUploadService {

    @Autowired
    S3Client s3Client;
    @Value("${rustfs.s3.bucket}")
    String bucketName;
    @Value("${rustfs.s3.chunk-size}")
    DataSize chunkSize;
    @Autowired
    S3Presigner s3Presigner;


    public UploadInitResp init(UploadInitReq req) {
        String objectKey =  req.getFileKey();

        // 秒传判断（HEAD）
        try {
            s3Client.headObject(
                    HeadObjectRequest.builder()
                            .bucket(bucketName)
                            .key(objectKey)
                            .build()
            );

            return UploadInitResp.builder()
                    .instant(true)
                    .objectKey(objectKey)
                    .build();

        } catch (NoSuchKeyException ignored) {
        }

        // 初始化 multipart
        CreateMultipartUploadResponse resp =
                s3Client.createMultipartUpload(
                        CreateMultipartUploadRequest.builder()
                                .bucket(bucketName)
                                .key(objectKey)
                                .build()
                );

        int chunkCount = (int) Math.ceil(
                (double) req.getFileSize() / chunkSize.toBytes()
        );

        return UploadInitResp.builder()
                .uploadId(resp.uploadId())
                .objectKey(objectKey)
                .chunkSize(chunkSize.toBytes())
                .chunkCount(chunkCount)
                .instant(false)
                .build();
    }

    public String presign(String objectKey, String uploadId, Integer partNumber) {
        // 上传分片信息获取临时put url
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .uploadId(uploadId)     // 必须
                .partNumber(partNumber) // 必须，每片不同
                .build();
        PresignedUploadPartRequest presignedRequest = s3Presigner.presignUploadPart(b -> b
                .uploadPartRequest(uploadPartRequest)
                .signatureDuration(Duration.ofMinutes(10)));
        return presignedRequest.url().toString();
    }

    public List<UploadedPartVO> listParts(String objectKey, String uploadId) {
        ListPartsResponse resp =
                s3Client.listParts(
                        ListPartsRequest.builder()
                                .bucket(bucketName)
                                .key(objectKey)
                                .uploadId(uploadId)
                                .build()
                );

        return resp.parts().stream()
                .map(p -> new UploadedPartVO(
                        p.partNumber(),
                        p.eTag()
                ))
                .toList();
    }

    public void complete(String objectKey, String uploadId) {
        ListPartsResponse list =
                s3Client.listParts(
                        ListPartsRequest.builder()
                                .bucket(bucketName)
                                .key(objectKey)
                                .uploadId(uploadId)
                                .build()
                );

        List<CompletedPart> parts = list.parts().stream()
                .sorted(Comparator.comparingInt(Part::partNumber))
                .map(p -> CompletedPart.builder()
                        .partNumber(p.partNumber())
                        .eTag(p.eTag())
                        .build()
                )
                .toList();

        s3Client.completeMultipartUpload(
                CompleteMultipartUploadRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .uploadId(uploadId)
                        .multipartUpload(
                                CompletedMultipartUpload.builder()
                                        .parts(parts)
                                        .build()
                        )
                        .build()
        );
    }

    public void abort(String objectKey, String uploadId) {
        try {
            s3Client.abortMultipartUpload(
                    AbortMultipartUploadRequest.builder()
                            .bucket(bucketName)
                            .key(objectKey)
                            .uploadId(uploadId)
                            .build()
            );
        } catch (NoSuchUploadException ignored) {
        }
    }
}