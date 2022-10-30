package ru.geekbrains.product_service.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.product_service.constants.ConstantsMessage;
import ru.geekbrains.product_service.converters.ProductConverter;
import ru.geekbrains.product_service.dto.ProductDto;
import ru.geekbrains.product_service.entities.Product;
import ru.geekbrains.product_service.excepttions.ResourceNotFoundException;
import ru.geekbrains.product_service.services.ProductsService;
import ru.geekbrains.product_service.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Продукты", description = "Контроллер работающий с продуктами")
public class ProductController {

    private final ProductConverter productConverter;
    private final ProductsService productsService;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.findAll(minPrice, maxPrice, titlePart, page).map(
                productConverter::entityToDto
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productsService.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantsMessage.PRODUCT_NOT_FOUND.getMessage() + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    @Operation(summary = "Запрос на сохранение нового продукта")
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }


}
