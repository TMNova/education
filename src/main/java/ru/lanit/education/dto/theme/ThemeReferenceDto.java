package ru.lanit.education.dto.theme;

import lombok.Builder;
import lombok.Data;

/**
 * ДТО для ссылок на другие темы
 */
@Data
@Builder
public class ThemeReferenceDto {

    private Long id;

    private String theme;
}
