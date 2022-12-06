package ru.lanit.education.mapper;

import org.mapstruct.Mapper;
import ru.lanit.education.domain.entity.TestProgressInfo;
import ru.lanit.education.dto.test.TestProgressInfoDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestProgressInfoMapper {

    TestProgressInfoDto toDto(TestProgressInfo entity);

    List<TestProgressInfoDto> toDto(List<TestProgressInfo> entity);
}
