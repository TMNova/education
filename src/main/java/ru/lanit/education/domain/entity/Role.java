package ru.lanit.education.domain.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователей
 */
public enum Role implements GrantedAuthority {

    ROLE_ADMIN,

    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
