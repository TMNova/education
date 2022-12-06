package ru.lanit.education.service.filestore;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import ru.lanit.education.dto.minio.UploadResponseDto;

/**
 * Сервис для взаимодействия с файловым хранилищем
 */
public interface FileStoreService {

    /**
     * Метод для получения файла из ФХ
     *
     * @param filename наименование файла
     * @return {@link InputStreamResource} полученный файл
     */
    InputStreamResource download(String filename);

    /**
     * Метод для загрузки файла в ФХ
     *
     * @param multipartFile файл для загрузки в ФХ
     * @return {@link UploadResponseDto} ДТО, содержащий данные, полученные после сохранения файла
     */
    UploadResponseDto upload(MultipartFile multipartFile);
}
