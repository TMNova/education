package ru.lanit.education.exception;

public class TestException extends RuntimeException {

    public static final String TEST_EXIST_ERROR = "Тест уже существует";

    public static final String TEST_NOT_EXIST_ERROR = "Тест не найден";

    public TestException() {
        super();
    }

    public TestException(String message) {
        super(message);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
