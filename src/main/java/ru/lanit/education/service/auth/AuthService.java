package ru.lanit.education.service.auth;

import ru.lanit.education.dto.auth.LoginDto;
import ru.lanit.education.dto.auth.SignUpDto;
import ru.lanit.education.dto.user.UserDto;

/**
 * Сервис для аутентификации пользователя
 */
public interface AuthService {

    /**
     * Метод для авторизации пользователя
     *
     * @param loginDto ДТО, содержащий данные для авторизации
     */
    void authUser(LoginDto loginDto);

    /**
     * Метод для регистрации пользователя
     *
     * @param signUpDto ДТО, содержащий данные для регистрации
     * @return {@link UserDto} ДТО, содержащий данные полученные после регистрации
     */
    UserDto registration(SignUpDto signUpDto);
}
