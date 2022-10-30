package ru.geekbrains.product_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @Schema(description = "Наименование продукта") // подписываем поля для Swagger(Чтобы бвло понятно другим пользователям)
    private String title;
    @Schema(description = "Цена продукта")
    private Integer price;
}
