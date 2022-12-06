package ru.lanit.education.mapper;

import org.mapstruct.Mapper;
import ru.lanit.education.domain.entity.Tag;
import ru.lanit.education.dto.tag.TagDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    List<TagDto> toDto(List<Tag> entity);

    TagDto toDto(Tag entity);

    Tag toEntity(TagDto dto);

    List<Tag> toEntity(List<TagDto> dto);
}
