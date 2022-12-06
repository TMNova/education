package ru.lanit.education.mapper;

import org.mapstruct.Mapper;
import ru.lanit.education.domain.entity.Test;
import ru.lanit.education.dto.test.TestDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    List<TestDto> toDto(List<Test> entity);

    TestDto toDto(Test entity);
}
