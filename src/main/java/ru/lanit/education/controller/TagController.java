package ru.lanit.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lanit.education.dto.tag.TagDto;
import ru.lanit.education.service.tag.TagService;

import java.util.List;

/**
 * REST контроллер для взаимодействия с тегами
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    /**
     * Эндпоинт для получения списка имеющихся тегов
     *
     * @return список {@link TagDto}
     */
    @GetMapping(
            value = "/tags",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TagDto>> getTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    /**
     * Эндпоинт для добавления нового тега
     *
     * @param tagDto ДТО, содержащий данные по тегу
     * @return {@link TagDto} ДТО, содержащий данные по созданному тегу
     */
    @PostMapping(
            value = "/tag",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TagDto> addTag(@RequestBody TagDto tagDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.addTag(tagDto));
    }

    /**
     * Эндпоинт для редактирования тега
     *
     * @param tagDto ДТО, содержащий данные по тегу
     * @return {@link TagDto} ДТО, содержащий данные по отредактированному тегу
     */
    @PutMapping(
            value = "/tag",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<TagDto> editTag(@RequestBody TagDto tagDto) {
        return ResponseEntity.ok(tagService.editTag(tagDto));
    }

    /**
     * Эндпоинт для удаления тега
     *
     * @param tagId ID тега, который необходимо удалить
     */
    @DeleteMapping(value = "/tag/{tagId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.noContent().build();
    }
}
