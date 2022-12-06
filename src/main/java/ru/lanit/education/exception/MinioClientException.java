package ru.lanit.education.exception;

public class MinioClientException extends RuntimeException {

    public static final String MINIO_CLIENT_INIT_ERROR = "Ошибка при инициализации клиента MinIO";

    public static final String MINIO_CLIENT_REQUEST_ERROR = "Ошибка при запросе в MinIO";

    public MinioClientException() {
        super();
    }

    public MinioClientException(String message) {
        super(message);
    }

    public MinioClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
