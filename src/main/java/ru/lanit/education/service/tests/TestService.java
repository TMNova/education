package ru.lanit.education.service.tests;

import ru.lanit.education.domain.entity.Test;
import ru.lanit.education.dto.test.TestDto;
import ru.lanit.education.dto.test.TestProgressInfoDto;

import java.util.List;

/**
 * Сервис для взаимодействия с тестами
 */
public interface TestService {

    /**
     * Метод для получения тестов
     *
     * @return список {@link TestDto}
     */
    List<TestDto> getTests();

    /**
     * Метод для получения теста по ID
     *
     * @param testId ID теста
     * @return {@link TestDto} ДТО, содержащий данные по тесту
     */
    TestDto getTestById(Long testId);

    /**
     * Метод для получения тестов по ID пользователя
     *
     * @param userId ID пользователя
     * @return список {@link TestProgressInfoDto}
     */
    List<TestProgressInfoDto> getUserProgressTests(Long userId);

    /**
     * Метод для получения теста пользователя по ID теста и ID пользователя
     *
     * @param userId ID пользователя
     * @param testId ID теста
     * @return {@link TestProgressInfoDto} ДТО, содержащий данные по тесту
     */
    TestProgressInfoDto getUserProgressTest(Long userId, Long testId);

    /**
     * Метод для получения сущности теста по ID
     *
     * @param testId ID теста
     * @return {@link Test} сущность, содержащая данные по тесту
     */
    Test getTestEntity(Long testId);

    /**
     * Метод для добавления сущности прогресса по тесту
     *
     * @param userId ID пользователя
     * @param testId ID теста
     * @return {@link Long} ID созданного сущности прогресса теста
     */
    Long addUserProgressTest(Long userId, Long testId);

    /**
     * Метод для удаления сущности прогресса по тесту
     *
     * @param userId ID пользователя
     * @param testId ID теста
     */
    void cancelUserTestProgress(Long userId, Long testId);
}
