package ru.lanit.education.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность тестов
 */
@Entity
@Getter
@Setter
public class Test {

    /**
     * ID теста
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование
     */
    private String name;

    /**
     * Описание
     */
    private String description;

    /**
     * Уровень
     */
    private String level;

    /**
     * Количество вопросов
     */
    private Integer questionCount;

    /**
     * Изображение
     */
    @Lob
    private String img;

    /**
     * Сущность пользователя
     */
    @ManyToMany(mappedBy = "tests")
    private List<User> users;

    /**
     * Сущность тега
     */
    @ManyToOne
    @JoinColumn(name = "tags_id")
    private Tag tag;

    /**
     * Сущность прогресса по тесту
     */
    @OneToOne(mappedBy = "test")
    private TestProgressInfo testProgressInfo;
}
