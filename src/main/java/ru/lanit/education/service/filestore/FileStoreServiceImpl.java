package ru.lanit.education.service.filestore;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.lanit.education.adapter.MinioAdapter;
import ru.lanit.education.dto.minio.UploadResponseDto;

import java.nio.channels.MulticastChannel;

@Service
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private final MinioAdapter minioAdapter;

    @Override
    public InputStreamResource download(String filename) {
        return minioAdapter.download(filename);
    }

    @Override
    public UploadResponseDto upload(MultipartFile multipartFile) {
        return minioAdapter.upload(multipartFile);
    }
}
