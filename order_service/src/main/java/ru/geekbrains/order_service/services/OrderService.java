package ru.geekbrains.order_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.order_service.converters.OrderConverter;
import ru.geekbrains.order_service.dto.OrderDto;
import ru.geekbrains.order_service.entities.Order;
import ru.geekbrains.order_service.repositories.OrderRepository;


import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderConverter orderConverter;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void saveOrder(OrderDto orderDto) {
        orderRepository.save(orderConverter.dtoToEntity(orderDto));

    }

    public List<Order> findOrdersByUsername(String username) {
        try {
            return orderRepository.findByUsername(username);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
