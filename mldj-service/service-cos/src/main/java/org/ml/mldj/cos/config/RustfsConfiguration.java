package org.ml.mldj.cos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;

import java.net.URI;

@Configuration
public class RustfsConfiguration {
    @Value("${rustfs.accessKey}")
    String accessKeyId;
    @Value("${rustfs.url}")
    String url;
    @Value("${rustfs.secret}")
    String secretAccessKey;

    @Bean
    public S3Client s3Client() {
        // 1. 初始化 S3 客户端
        S3Client s3 = S3Client.builder()
                .endpointOverride(URI.create(url)) // RustFS 地址
                .region(Region.US_EAST_1) // 可写死，RustFS 不校验 region
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKeyId, secretAccessKey)
                        )
                )
                .forcePathStyle(true) // 关键配置！RustFS 需启用 Path-Style
                .build();
        // 检查桶是否存在，如果不存在则创建
        String bucket = "my-bucket";
        HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucket)
                .build();
        s3.headBucket(headBucketRequest);
//        s3.bucket
        return s3;
    }
}
