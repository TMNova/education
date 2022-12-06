package ru.lanit.education.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lanit.education.domain.entity.Theme;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий тем
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("SELECT t.id, t.theme, t.description FROM Theme t WHERE t.test.id = :testId")
    List<Theme> findIdThemeDescriptionByTestId(@Param("testId") Long testId);

    @Query("SELECT t.id, t.theme FROM Theme t WHERE t.id = :id")
    Optional<Theme> findIdAndThemeById(@Param("id") Long id);

    Optional<Theme> findByIdAndTest_Id(Long themeId, Long testId);
}
