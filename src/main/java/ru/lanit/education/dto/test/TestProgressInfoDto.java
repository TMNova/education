package ru.lanit.education.dto.test;

import lombok.Data;
import ru.lanit.education.domain.entity.TestProgressResult;

/**
 * ДТО для прогресса тестов
 */
@Data
public class TestProgressInfoDto {

    /**
     * ID теста
     */
    private Long testId;

    /**
     * Прогресс
     */
    private Integer progress;

    /**
     * Маркер
     */
    private String marker;

    /**
     * Статус прогресса теста
     */
    private TestProgressResult result;
}
