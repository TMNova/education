package ru.lanit.education.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lanit.education.domain.entity.Tag;

import java.util.Optional;

/**
 * Репозиторий тегов
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);

    boolean existsByName(String name);
}
