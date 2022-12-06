package ru.lanit.education.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Внешние конфигурационные проперти для MinIO
 */
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioConfigProperties {

    private String url;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
