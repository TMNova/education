package ru.lanit.education.dto.auth;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * ДТО для регистрации пользователя
 */
@Data
public class SignUpDto {

    /**
     * Имя
     */
    @NonNull
    private String firstName;

    /**
     * Фамилия
     */
    @NonNull
    private String lastName;

    /**
     * e-mail
     */
    @NonNull
    private String email;

    /**
     * Пароль
     */
    @NonNull
    private String password;
}
