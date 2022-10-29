package ru.geekbrains.auth_service.constants;

public enum ConstantsMessage {
    USER_NOT_FOUND("User '%s' not found"),
    INCORRECT_USERNAME_PASSWORD("Incorrect username or password");

    private final String message;


    ConstantsMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ConstantsMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
