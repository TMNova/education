package ru.lanit.education.mapper;

import org.mapstruct.Mapper;
import ru.lanit.education.domain.entity.Theme;
import ru.lanit.education.dto.theme.ThemeDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    ThemeDto toDto(Theme entity);

    List<ThemeDto> toDto(List<Theme> entity);

    Theme toEntity(ThemeDto dto);

    List<Theme> toEntity(List<ThemeDto> dto);
}
