package ru.geekbrains.product_service.validators;


import org.springframework.stereotype.Component;
import ru.geekbrains.product_service.constants.ConstantsMessage;
import ru.geekbrains.product_service.dto.ProductDto;
import ru.geekbrains.product_service.excepttions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() < 1) {
            errors.add(ConstantsMessage.PRICE_NOT_NULL.getMessage());
        }
        if (productDto.getTitle().isBlank()) {
            errors.add(ConstantsMessage.TITLE_NOT_NULL.getMessage());
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }


}
