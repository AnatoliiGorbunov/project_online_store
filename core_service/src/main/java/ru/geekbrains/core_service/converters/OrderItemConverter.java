package ru.geekbrains.core_service.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.core_service.dto.OrderItemDto;
import ru.geekbrains.core_service.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
