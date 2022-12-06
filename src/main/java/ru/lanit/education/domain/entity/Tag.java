package ru.lanit.education.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность тегов
 */
@Entity
@Getter
@Setter
public class Tag {

    /**
     * ID тега
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование тега
     */
    @Column(unique = true)
    private String name;
}
