package ru.geekbrains.project_online_store.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.project_online_store.dto.Cart;
import ru.geekbrains.project_online_store.dto.OrderDetailsDto;
import ru.geekbrains.project_online_store.entities.Order;
import ru.geekbrains.project_online_store.entities.OrderItem;
import ru.geekbrains.project_online_store.entities.User;
import ru.geekbrains.project_online_store.exceptions.ResourceNotFoundException;
import ru.geekbrains.project_online_store.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductsService productsService;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto, String cartName) {
        Cart currentCart = cartService.getCurrentCart(cartName);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(o.getQuantity());
                    orderItem.setPricePerProduct(o.getPricePerProduct());
                    orderItem.setPrice(o.getPrice());
                    orderItem.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        currentCart.clear();
    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
