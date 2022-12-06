package ru.lanit.education.dto.theme;

import lombok.Data;

/**
 * ДТО для тем
 */
@Data
public class ThemeDto {

    /**
     * ID темы
     */
    private Long id;

    /**
     * Номер темы
     */
    private Integer number;

    /**
     * Наименование темы
     */
    private String theme;

    /**
     * Описание
     */
    private String description;

    /**
     * Контент
     */
    private String content;

    /**
     * Следующая тема
     */
    private ThemeReferenceDto next;

    /**
     * Предыдущая тема
     */
    private ThemeReferenceDto prev;
}
