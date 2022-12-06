package ru.lanit.education.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность прогресса по тесту
 */
@Entity
@Getter
@Setter
public class TestProgressInfo {

    /**
     * ID сущности прогресса по тесту
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Прогресс (0-100%)
     */
    private Integer progress;

    /**
     * Маркер
     */
    private String marker;

    /**
     * Сущность теста
     */
    @OneToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    /**
     * Сущность пользователя
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Статус сущности прогресса по тесту
     */
    @Enumerated(value = EnumType.STRING)
    private TestProgressResult result;
}
