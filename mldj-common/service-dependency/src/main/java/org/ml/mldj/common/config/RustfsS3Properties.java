package org.ml.mldj.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@ConfigurationProperties(prefix = "rustfs.s3")
@Getter
@Setter
public class RustfsS3Properties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private Region region = Region.US_EAST_1;
    private String bucket;
    private Boolean initBucket;

    public StaticCredentialsProvider buildCredentials() {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey));
    }


}
