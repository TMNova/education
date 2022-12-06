package ru.lanit.education.service.user;

import ru.lanit.education.domain.entity.User;
import ru.lanit.education.dto.user.UserDto;

import java.util.List;

/**
 * Сервис для взаимодействия с пользователями
 */
public interface UserService {

    /**
     * Метод для получения пользователей
     *
     * @return список {@link UserDto}
     */
    List<UserDto> getUsers();

    /**
     * Метод для получения пользователя по ID
     *
     * @param userId ID пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    UserDto getUserById(Long userId);

    /**
     * Метод для получения сущности пользователя по ID
     *
     * @param userId ID пользователя
     * @return {@link UserDto} сущность, содержащая данные по пользователю
     */
    User getUserEntityById(Long userId);

    /**
     * Метод для редактирования данных пользователя
     *
     * @param user ДТО, содержащий данные для редактирования пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    UserDto editUser(UserDto user);

    /**
     * Метод для добавления пользователя
     *
     * @param user ДТО, содержащий данные нового пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    UserDto addUser(UserDto user);

    /**
     * Метод для удаления пользователя
     *
     * @param userId ID пользователя
     */
    void deleteUser(Long userId);
}
