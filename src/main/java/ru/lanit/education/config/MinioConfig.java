package ru.lanit.education.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.lanit.education.exception.MinioClientException;

import java.util.concurrent.TimeUnit;

/**
 * Кофигурационный файл для возможности коннекта с MinIO
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MinioConfigProperties.class)
@Slf4j
public class MinioConfig {

    private final MinioConfigProperties properties;

    /**
     * Создание бина с {@link MinioClient}
     */
    @Bean
    public MinioClient minioClient() {
        try {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();

            return MinioClient.builder()
                    .endpoint(properties.getUrl())
                    .httpClient(httpClient)
                    .credentials(properties.getAccessKey(), properties.getSecretKey())
                    .build();
        } catch (Exception e) {
            log.error(MinioClientException.MINIO_CLIENT_INIT_ERROR, e);
            throw new MinioClientException(MinioClientException.MINIO_CLIENT_INIT_ERROR);
        }
    }
}
