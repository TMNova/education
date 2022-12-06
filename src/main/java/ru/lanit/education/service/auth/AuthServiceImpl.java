package ru.lanit.education.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lanit.education.domain.entity.Role;
import ru.lanit.education.domain.entity.User;
import ru.lanit.education.domain.repository.UserRepository;
import ru.lanit.education.dto.auth.LoginDto;
import ru.lanit.education.dto.auth.SignUpDto;
import ru.lanit.education.dto.user.UserDto;
import ru.lanit.education.exception.UserException;
import ru.lanit.education.mapper.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManagerBean;

    @Override
    public void authUser(LoginDto loginDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                authenticationManagerBean.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserDto registration(SignUpDto signUpDto) {
        if (userRepository.existsUserByEmail(signUpDto.getEmail())) {
            log.error(UserException.USER_EXIST_ERROR);
            throw new UserException(UserException.USER_EXIST_ERROR);
        }

        User saveUser = new User();
        saveUser.setEmail(signUpDto.getEmail());
        saveUser.setPassword(
                passwordEncoder.encode(signUpDto.getPassword())
        );
        saveUser.setFirstName(signUpDto.getFirstName());
        saveUser.setLastName(signUpDto.getLastName());
        saveUser.setRoles(List.of(Role.ROLE_USER));

        User newUser = userRepository.save(saveUser);

        return userMapper.toDto(newUser);
    }
}
