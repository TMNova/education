package ru.lanit.education.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Сущность пользователя
 */
@Entity
@Getter
@Setter
@Table(name = "usr")
public class User implements UserDetails {

    /**
     * ID пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Пароль
     */
    private String password;

    /**
     * e-mail
     */
    @Column(unique = true)
    private String email;

    /**
     * Роли пользователя
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")})
    @Enumerated(value = EnumType.STRING)
    private List<Role> roles;

    /**
     * Номер телефона
     */
    @Column(unique = true)
    private String phone;

    /**
     * Сущность со списком тестов
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_tests",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")}
    )
    private List<Test> tests;

    /**
     * Аватар пользователя
     */
    private String avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
