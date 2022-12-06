package ru.lanit.education.dto.auth;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * ДТО для авторизации пользователя
 */
@Data
public class LoginDto {

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
