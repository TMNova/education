package ru.lanit.education.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.education.domain.entity.Test;

/**
 * Репозиторий тестов
 */
public interface TestRepository extends JpaRepository<Test, Long> {
}
