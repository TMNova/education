package ru.lanit.education.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.education.domain.entity.User;

/**
 * Репозиторий пользователей
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
