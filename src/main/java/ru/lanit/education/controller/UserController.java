package ru.lanit.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.lanit.education.dto.test.TestProgressInfoDto;
import ru.lanit.education.dto.user.UserDto;
import ru.lanit.education.service.user.UserService;

import java.util.List;

/**
 * REST контроллер для взаимодействия с пользователями
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Эндпоинт для получения пользователей
     *
     * @return список {@link UserDto}
     */
    @GetMapping(
            value = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * Эндпоинт для получения пользователя по ID
     *
     * @param userId ID пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    @GetMapping(
            value = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    /**
     * Эндпоинт для редактирования данных пользователя
     *
     * @param userDto ДТО, содержащий данные для редактирования пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    @PutMapping(
            value = "/user/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(userDto));
    }

    /**
     * Эндпоинт для добавления пользователя
     *
     * @param userDto ДТО, содержащий данные нового пользователя
     * @return {@link UserDto} ДТО, содержащий данные по пользователю
     */
    @PostMapping(
            value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Эндпоинт для удаления пользователя
     *
     * @param userId ID пользователя
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
