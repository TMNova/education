package ru.lanit.education.exception;

public class TagException extends RuntimeException {

    public static final String TAG_EXIST_ERROR = "Тег уже существует";

    public static final String TAG_NOT_EXIST_ERROR = "Тег не найден";

    public TagException() {
        super();
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }
}
