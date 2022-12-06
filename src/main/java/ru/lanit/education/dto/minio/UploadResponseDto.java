package ru.lanit.education.dto.minio;

import lombok.Builder;
import lombok.Data;

/**
 * ДТО для ответа после загрузки файла в MinIO
 */
@Data
@Builder
public class UploadResponseDto {

    /**
     * Наименование бакета
     */
    private String bucketName;

    /**
     * Наименование сохраненного файла
     */
    private String objectName;
}
