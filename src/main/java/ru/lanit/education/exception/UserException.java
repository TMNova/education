package ru.lanit.education.exception;

public class UserException extends RuntimeException {

    public static final String EMPTY_DATA_ERROR = "Не заданы данные для регистрации пользователя";

    public static final String USER_EXIST_ERROR = "Пользователь уже существует";

    public static final String USER_NOT_EXIST_ERROR = "Пользователь не найден";

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
