package ru.geekbrains.project_online_store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.geekbrains.project_online_store.dto.Cart;
import ru.geekbrains.project_online_store.entities.Product;
import ru.geekbrains.project_online_store.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductsService productsService;
    @Qualifier("test")
    private final CacheManager cacheManager;
    @Value("${spring.cache.user.name}")
    private String CACHE_CART;
    private Cart cart;

    @Cacheable(value = "Cart", key = "#cartName")
    public Cart getCurrentCart(String cartName) {
        cart = cacheManager.getCache(CACHE_CART).get(cartName, Cart.class);
        if (!Optional.ofNullable(cart).isPresent()) {
            cart = new Cart(cartName, cacheManager);
            cacheManager.getCache(CACHE_CART).put(cartName, cart);
        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public Cart addProductByIdToCart(Long id, String cartName) {
        Cart cart = getCurrentCart(cartName);
        if (!cart.addProductCount(id)) {
            Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт"));
            cart.addProduct(product);
        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public Cart clear(String cartName) {
        Cart cart = getCurrentCart(cartName);
        cart.clear();
        return cart;
    }
}
