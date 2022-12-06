package ru.lanit.education.controller;

import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.lanit.education.dto.minio.UploadResponseDto;
import ru.lanit.education.dto.user.UserDto;
import ru.lanit.education.service.filestore.FileStoreService;

/**
 * REST контроллер для взаимодействия с файловым хранилищем
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filestore")
public class FileStoreController {

    private final FileStoreService fileStoreService;

    /**
     * Эндпоинт для получения файла из ФХ
     *
     * @param filename наименование файла
     * @return {@link InputStreamResource} полученный файл
     */
    @GetMapping(
            value = "/download",
            produces = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<InputStreamResource> download(@RequestParam String filename) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .body(fileStoreService.download("file"));
    }

    /**
     * Эндпоинт для загрузки файла в ФХ
     *
     * @param file файл для загрузки в ФХ
     * @return {@link UploadResponseDto} ДТО, содержащий данные, полученные после сохранения файла
     */
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UploadResponseDto> upload(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(fileStoreService.upload(file));
    }
}
