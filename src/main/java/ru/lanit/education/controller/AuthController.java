package ru.lanit.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lanit.education.dto.auth.LoginDto;
import ru.lanit.education.dto.auth.SignUpDto;
import ru.lanit.education.dto.user.UserDto;
import ru.lanit.education.service.auth.AuthService;

/**
 * REST контроллер для аутентификации пользователя (logout релизован в {@link ru.lanit.education.config.WebSecurityConfig})
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Эндпоинт для авторизации пользователя
     *
     * @param loginDto ДТО, содержащий данные для авторизации
     * @return {@link UserDto} ДТО, содержащий данные полученные после авторизации
     */
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        authService.authUser(loginDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для регистрации пользователя
     *
     * @param signUpDto ДТО, содержащий данные для регистрации
     * @return {@link UserDto} ДТО, содержащий данные полученные после регистрации
     */
    @PostMapping(
            value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> registration(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authService.registration(signUpDto));
    }
}
