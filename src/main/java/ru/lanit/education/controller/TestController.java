package ru.lanit.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lanit.education.dto.tag.TagDto;
import ru.lanit.education.dto.test.TestDto;
import ru.lanit.education.dto.test.TestProgressInfoDto;
import ru.lanit.education.dto.test.TestRequestDto;
import ru.lanit.education.dto.theme.ThemeDto;
import ru.lanit.education.service.tests.TestService;
import ru.lanit.education.service.theme.ThemeService;

import java.util.List;

/**
 * REST контроллер для взаимодействия с тестами
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestService testService;

    private final ThemeService themeService;

    /**
     * Эндпоинт для получения тестов по ID пользователя
     *
     * @param userId ID пользователя
     * @return список {@link TestProgressInfoDto}
     */
    @GetMapping(
            value = "/user/{userId}/tests",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TestProgressInfoDto>> getUserProgressTests(@PathVariable Long userId) {
        return ResponseEntity.ok(testService.getUserProgressTests(userId));
    }

    /**
     * Эндпоинт для получения теста пользователя по ID теста и ID пользователя
     *
     * @param userId ID пользователя
     * @param testId ID теста
     * @return {@link TestProgressInfoDto} ДТО, содержащий данные по тесту
     */
    @GetMapping(
            value = "/user/{userId}/test/{testId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TestProgressInfoDto> getUserProgressTest(@PathVariable Long userId, @PathVariable Long testId) {
        return ResponseEntity.ok(testService.getUserProgressTest(userId, testId));
    }

    /**
     * Эндпоинт для добавления сущности прогресса по тесту
     *
     * @param userId ID пользователя
     * @param requestDto ДТО, содержащий данные по прогрессу теста
     * @return {@link Long} ID созданного сущности прогресса теста
     */
    @PostMapping(
            value = "/user/{userId}/test",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> addUserProgressTest(@PathVariable Long userId, @RequestBody TestRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(testService.addUserProgressTest(userId, requestDto.getTestId()));
    }

    /**
     * Эндпоинт для удаления сущности прогресса по тесту
     *
     * @param userId ID пользователя
     * @param testId ID теста
     */
    @DeleteMapping("/user/{userId}/test/{testId}")
    public ResponseEntity<Object> cancelUserTestProgress(@PathVariable Long userId, @PathVariable Long testId) {
        testService.cancelUserTestProgress(userId, testId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Эндпоинт для получения тестов
     *
     * @return список {@link TestDto}
     */
    @GetMapping(
            value = "/tests",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TestDto>> getTests() {
        return ResponseEntity.ok(testService.getTests());
    }

    /**
     * Эндпоинт для получения теста по ID
     *
     * @param testId ID теста
     * @return {@link TestDto} ДТО, содержащий данные по тесту
     */
    @GetMapping(
            value = "/test/{testId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TestDto> getTestById(@PathVariable Long testId) {
        return ResponseEntity.ok(testService.getTestById(testId));
    }

    /**
     * Эндпоинт для получения тем по ID теста
     *
     * @param testId ID теста
     * @return список {@link ThemeDto}
     */
    @GetMapping(
            value = "/test/{testId}/themes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ThemeDto>> getThemes(@PathVariable Long testId) {
        return ResponseEntity.ok(themeService.getThemesByTestId(testId));
    }

    /**
     * Эндпоинт для получения темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param themeId ID темы
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    @GetMapping(
            value = "/test/{testId}/theme/{themeId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ThemeDto> getThemeByTestIdAndThemeId(@PathVariable Long testId, @PathVariable Long themeId) {
        return ResponseEntity.ok(themeService.getThemeByTestIdAndThemeId(testId, themeId));
    }

    /**
     * Эндпоинт для изменения темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param themeId ID темы
     * @param theme ДТО, содержащий данные для редактирования по теме
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    @PutMapping(
            value = "/test/{testId}/theme/{themeId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ThemeDto> editThemeByTestIdAndThemeId(@PathVariable Long themeId, @RequestBody ThemeDto theme, @PathVariable String testId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(themeService.editTheme(theme));
    }

    /**
     * Эндпоинт для добавление темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param theme ДТО, содержащий данные для добавление темы
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    @PostMapping(
            value = "/test/{testId}/theme",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ThemeDto> addThemeByTestId(@PathVariable Long testId, @RequestBody ThemeDto theme) {
        return ResponseEntity.ok(themeService.addTheme(theme, testId));
    }

    /**
     * Эндпоинт для удаления темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param themeId ID темы
     */
    @DeleteMapping(value = "/test/{testId}/theme/{themeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteTheme(@PathVariable Long testId, @PathVariable Long themeId) {
        themeService.deleteTheme(themeId);
        return ResponseEntity.noContent().build();
    }
}
