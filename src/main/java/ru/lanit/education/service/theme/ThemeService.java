package ru.lanit.education.service.theme;

import ru.lanit.education.dto.theme.ThemeDto;

import java.util.List;

/**
 * Сервис для взаимодействия с темами тестов
 */
public interface ThemeService {

    /**
     * Эндпоинт для получения тем по ID теста
     *
     * @param testId ID теста
     * @return список {@link ThemeDto}
     */
    List<ThemeDto> getThemesByTestId(Long testId);

    /**
     * Эндпоинт для получения темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param themeId ID темы
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    ThemeDto getThemeByTestIdAndThemeId(Long testId, Long themeId);

    /**
     * Эндпоинт для изменения темы
     *
     * @param themeDto ДТО, содержащий данные для редактирования по теме
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    ThemeDto editTheme(ThemeDto themeDto);

    /**
     * Эндпоинт для добавление темы по ID теста и ID темы
     *
     * @param testId ID теста
     * @param themeDto ДТО, содержащий данные для добавление темы
     * @return {@link ThemeDto} ДТО, содержащий данные по теме
     */
    ThemeDto addTheme(ThemeDto themeDto, Long testId);

    /**
     * Эндпоинт для удаления темы по ID темы
     *
     * @param themeId ID темы
     */
    void deleteTheme(Long themeId);
}
