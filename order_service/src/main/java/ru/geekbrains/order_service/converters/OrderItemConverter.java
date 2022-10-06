package ru.geekbrains.order_service.converters;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

}
