package org.ml.mldj.common.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
@ConditionalOnClass(S3Client.class)
@EnableConfigurationProperties(RustfsS3Properties.class)
public class RustfsS3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public S3Client rustfsS3Client(RustfsS3Properties props) {
        return S3Client.builder()
                .endpointOverride(URI.create(props.getEndpoint())) // RustFS 地址
                .region(Region.US_EAST_1) // 可写死，RustFS 不校验 region
                .credentialsProvider(props.buildCredentials())
                .forcePathStyle(true) // 关键配置！RustFS 需启用 Path-Style
                .build();
    }


    /// 初始化存储桶，默认是关闭的
    @Bean
    @ConditionalOnProperty(
            prefix = "rustfs.s3",
            name = "init-bucket",
            havingValue = "true",
            matchIfMissing = false
    )
    public CommandLineRunner initBuckets(S3Client client, RustfsS3Properties props) {
        return args -> {
            if (client.listBuckets().buckets().stream()
                    .noneMatch(b -> b.name().equals(props.getBucket()))) {
                client.createBucket(b -> b.bucket(props.getBucket()));
            }
        };
    }

    @Bean
    public S3Presigner s3Presigner(RustfsS3Properties props) {
        return S3Presigner.builder()
                .endpointOverride(URI.create(props.getEndpoint()))
                .region(Region.US_EAST_1)
                .credentialsProvider(props.buildCredentials())
                .serviceConfiguration(
                        S3Configuration.builder()
                                .pathStyleAccessEnabled(true)
                                .build()
                )
                .build();
    }
}
