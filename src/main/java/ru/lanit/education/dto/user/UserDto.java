package ru.lanit.education.dto.user;

import lombok.Builder;
import lombok.Data;
import ru.lanit.education.domain.entity.Role;

import java.util.List;

/**
 * ДТО для пользователя
 */
@Data
@Builder
public class UserDto {

    /**
     * ID пользователя
     */
    private Long id;

    /**
     * Имя
     */
    private String firstName;

    /**
     * Фамилия
     */
    private String lastName;

    /**
     * e-mail
     */
    private String email;

    /**
     * Роли пользователя
     */
    private List<Role> roles;

    /**
     * Телефонный номер
     */
    private String phone;

    /**
     * Аватар
     */
    private String avatar;
}
