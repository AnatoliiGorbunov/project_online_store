package ru.geekbrains.order_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
}
