package ru.geekbrains.project_online_store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.project_online_store.dto.Cart;
import ru.geekbrains.project_online_store.services.CartService;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping
    public Cart getCurrentCart(@RequestBody String cartName) {
        return service.getCurrentCart(cartName);
    }

    @PostMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id, @RequestBody String cartName) {
        service.addProductByIdToCart(id, cartName);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestBody String cartName) {
        service.clear(cartName);
    }
}
