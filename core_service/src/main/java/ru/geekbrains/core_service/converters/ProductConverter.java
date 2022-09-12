package ru.geekbrains.core_service.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.core_service.dto.ProductDto;
import ru.geekbrains.core_service.entities.Product;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
