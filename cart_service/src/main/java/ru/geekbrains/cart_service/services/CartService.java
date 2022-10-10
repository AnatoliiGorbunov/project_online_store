package ru.geekbrains.cart_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.cart_service.dto.Cart;
import ru.geekbrains.cart_service.dto.OrderDetailsDto;
import ru.geekbrains.cart_service.dto.OrderDto;
import ru.geekbrains.cart_service.dto.ProductDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CacheManager cacheManager;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<Long, OrderDto> kafkaTemplate;

    @Value("${spring.cache.user.name}")
    private String CACHE_CART;
    @Value("{spring.kafka.topic}")
    private String topic;

    @Cacheable(value = "Cart", key = "#cartName")
    public Cart getCurrentCart(String cartName) {
        Cart cart = cacheManager.getCache(CACHE_CART).get(cartName, Cart.class);
        if (!Optional.ofNullable(cart).isPresent()) {
            cart = new Cart(cartName, cacheManager);
            cacheManager.getCache(CACHE_CART).put(cartName, cart);
        }
        return cart;
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void addProductByIdToCart(Long id, String cartName) {
        Cart cart = getCurrentCart(cartName);
        if (!cart.addProductCount(id)) {
            ProductDto product =
                    restTemplate.getForObject("http://localhost:8189/web-market-core/api/v1/products/" + id, ProductDto.class);
            assert product != null;
            cart.addProduct(product);
        }
    }

    @CachePut(value = "Cart", key = "#cartName")
    public void clear(String cartName) {
        Cart cart = getCurrentCart(cartName);
        cart.clear();
    }

    public void createOrder(String username, OrderDetailsDto orderDetailsDto, String cartName) {
        Cart currentCart = getCurrentCart(cartName);
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(orderDetailsDto.getAddress());
        orderDto.setPhone(orderDetailsDto.getPhone());
        orderDto.setUsername(username);
        orderDto.setTotalPrice(currentCart.getTotalPrice());
        orderDto.setItemDtoList(currentCart.getItems());
        currentCart.clear();
        kafkaTemplate.send(topic, orderDto);
    }
}
