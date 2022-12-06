package ru.lanit.education.dto.test;

import lombok.Data;
import ru.lanit.education.dto.tag.TagDto;

/**
 * ДТО для тестов
 */
@Data
public class TestDto {

    /**
     * ID теста
     */
    private Long id;

    /**
     * Наименование теста
     */
    private String name;

    /**
     * Описание
     */
    private String description;

    /**
     * Уровень
     */
    private String level;

    /**
     * Количество вопросов
     */
    private Integer questionCount;

    /**
     * Изображение
     */
    private String img;

    /**
     * Тег
     */
    private TagDto tags;
}
