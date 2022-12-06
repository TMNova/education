package ru.lanit.education.adapter;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.lanit.education.config.MinioConfigProperties;
import ru.lanit.education.dto.minio.UploadResponseDto;
import ru.lanit.education.exception.MinioClientException;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

/**
 * Адаптер для MinIO (облачного хранилища данных)
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MinioAdapter {

    private final MinioClient minioClient;

    private final MinioConfigProperties properties;

    /**
     * Метод для скачивания файла через MinIO
     *
     * @param fileName наименование файла
     * @return {@link InputStreamResource} полученный файл
     */
    public InputStreamResource download(String fileName) {
        try {
            GetObjectArgs request = GetObjectArgs.builder()
                    .bucket(properties.getBucketName())
                    .object(fileName)
                    .build();

            return new InputStreamResource(minioClient.getObject(request));
        } catch (Exception e) {
            log.error(MinioClientException.MINIO_CLIENT_REQUEST_ERROR, e);
            throw new MinioClientException(MinioClientException.MINIO_CLIENT_REQUEST_ERROR);
        }
    }

    /**
     * Метод, позволяющий произвести загрузку файла в MinIO
     *
     * @param multipartFile файл
     * @return {@link UploadResponseDto} ДТО, хранящий полученные данные от MinIO
     */
    public UploadResponseDto upload(MultipartFile multipartFile) {
        File tempFile = null;
        try {
            tempFile = Files.createTempFile("file", "temp").toFile();

            multipartFile.transferTo(tempFile);
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(properties.getBucketName())
                    .object(multipartFile.getOriginalFilename())
                    .filename(tempFile.getAbsolutePath())
                    .build();

            ObjectWriteResponse response = minioClient.uploadObject(uploadObjectArgs);

            return UploadResponseDto.builder()
                    .bucketName(response.bucket())
                    .objectName(response.object())
                    .build();
        } catch (Exception e) {
            log.error(MinioClientException.MINIO_CLIENT_REQUEST_ERROR, e);
            throw new MinioClientException(MinioClientException.MINIO_CLIENT_REQUEST_ERROR);
        } finally {
            Objects.requireNonNull(tempFile).delete();
        }
    }
}
