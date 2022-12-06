package ru.lanit.education.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность темы
 */
@Entity
@Getter
@Setter
public class Theme {

    /**
     * ID темы
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер темы
     */
    private Integer number;

    /**
     * Наименование темы
     */
    private String theme;

    /**
     * Описание
     */
    private String description;

    /**
     * Контент
     */
    @Lob
    private String content;

    /**
     * Тип темы
     */
    //TODO Далее заменить на ENUM
    private String type;

    /**
     * Сущность теста
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;
}
