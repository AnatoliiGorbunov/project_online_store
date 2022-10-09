package ru.geekbrains.order_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.order_service.dto.OrderDetailsDto;
import ru.geekbrains.order_service.dto.OrderDto;
import ru.geekbrains.order_service.entities.Order;
import ru.geekbrains.order_service.entities.OrderItem;
import ru.geekbrains.order_service.repositories.OrderRepository;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;


    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setUsername(orderDto.getUsername());
        order.setTotalPrice(orderDto.getTotalPrice());
        List<OrderItem> items = orderDto.getItemDtoList().stream()
                .map(o -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(o.getQuantity());
                    orderItem.setPricePerProduct(o.getPricePerProduct());
                    orderItem.setPrice(o.getPrice());
                    orderItem.setTitle(o.getTitle());
                    return orderItem;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);

    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
