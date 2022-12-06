package ru.lanit.education.mapper;

import org.mapstruct.Mapper;
import ru.lanit.education.domain.entity.User;
import ru.lanit.education.dto.user.UserDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entity);

    User toEntity(UserDto dto);

    List<User> toEntity(List<UserDto> dto);
}
