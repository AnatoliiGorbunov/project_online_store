package ru.geekbrains.product_service.constants;


public enum ConstantsMessage {

    PRICE_NOT_NULL("Цена продукта должна быть больше 1"),
    TITLE_NOT_NULL("Продукт не может иметь пустое название"),
    INCORRECT_PRODUCT("Невозможно обновить продукт, не надйен в базе, id: "),
    PRODUCT_NOT_FOUND("Продукт не найден, id: ");

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
