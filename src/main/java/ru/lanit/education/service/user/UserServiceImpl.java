package ru.lanit.education.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lanit.education.domain.entity.User;
import ru.lanit.education.domain.repository.UserRepository;
import ru.lanit.education.dto.user.UserDto;
import ru.lanit.education.exception.UserException;
import ru.lanit.education.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error(UserException.USER_NOT_EXIST_ERROR);
                    return new UserException(UserException.USER_NOT_EXIST_ERROR);
                });
    }

    @Override
    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error(UserException.USER_NOT_EXIST_ERROR);
                    return new UserException(UserException.USER_NOT_EXIST_ERROR);
                });
    }

    @Override
    public UserDto editUser(UserDto user) {
        boolean userExist = userRepository.existsById(user.getId());

        if (userExist) {
            User userNewData = userMapper.toEntity(user);
            User savedUser = userRepository.save(userNewData);
            return userMapper.toDto(savedUser);
        } else {
            log.error(UserException.USER_NOT_EXIST_ERROR);
            throw new UserException(UserException.USER_NOT_EXIST_ERROR);
        }
    }

    @Override
    public UserDto addUser(UserDto user) {
        if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserException(UserException.USER_EXIST_ERROR);
        }

        User newUser = userMapper.toEntity(user);
        User savedUser = userRepository.save(newUser);

        return UserDto.builder()
                .id(savedUser.getId())
                .build();
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            log.error(UserException.USER_NOT_EXIST_ERROR);
            throw new UserException(UserException.USER_NOT_EXIST_ERROR);
        }

        userRepository.deleteById(userId);
    }
}
