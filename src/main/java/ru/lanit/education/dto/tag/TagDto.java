package ru.lanit.education.dto.tag;

import lombok.Data;

/**
 * ДТО для тегов
 */
@Data
public class TagDto {

    /**
     * ID тега
     */
    private Long id;

    /**
     * Наименование тега
     */
    private String name;

}
