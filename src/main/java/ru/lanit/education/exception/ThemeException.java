package ru.lanit.education.exception;

public class ThemeException extends RuntimeException {

    public static final String THEME_EXIST_ERROR = "Тема уже существует";

    public static final String THEME_NOT_EXIST_ERROR = "Тема не найдена";

    public ThemeException() {
        super();
    }

    public ThemeException(String message) {
        super(message);
    }

    public ThemeException(String message, Throwable cause) {
        super(message, cause);
    }
}
