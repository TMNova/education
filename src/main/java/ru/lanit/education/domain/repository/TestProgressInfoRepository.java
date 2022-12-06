package ru.lanit.education.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ru.lanit.education.domain.entity.TestProgressInfo;

import java.util.List;

/**
 * Репозиторий прогресса тестов
 */
public interface TestProgressInfoRepository extends JpaRepository<TestProgressInfo, Long> {

    List<TestProgressInfo> findAllByUser_Id(Long userId);

    TestProgressInfo findByUser_IdAndTest_Id(Long userId, Long testId);

    void deleteByUser_IdAndTest_Id(Long userId, Long testId);
}
