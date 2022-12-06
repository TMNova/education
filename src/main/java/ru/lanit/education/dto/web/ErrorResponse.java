package ru.lanit.education.dto.web;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ДТО для ответа при ошибке
 */
@Data
@Builder
public class ErrorResponse {

    /**
     * {@link HttpStatus} Http статус
     */
    private HttpStatus status;

    /**
     * Сообщение об ошибке
     */
    private String message;
}
