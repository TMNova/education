package ru.lanit.education.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.lanit.education.dto.web.ErrorResponse;
import ru.lanit.education.exception.TagException;
import ru.lanit.education.exception.TestException;
import ru.lanit.education.exception.ThemeException;
import ru.lanit.education.exception.UserException;

/**
 * Хэндлер для сущности ответа сервера
 */
@ControllerAdvice
@Slf4j
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserException.class, TagException.class, ThemeException.class, TestException.class})
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse apiError = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleBaseException(Exception ex) {
        log.error(ex.getMessage(), ex);
        ErrorResponse apiError = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.internalServerError().body(apiError);
    }

}
