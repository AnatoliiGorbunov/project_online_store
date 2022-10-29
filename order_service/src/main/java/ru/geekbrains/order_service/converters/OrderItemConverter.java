package ru.geekbrains.order_service.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.order_service.dto.OrderItemDto;
import ru.geekbrains.order_service.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getId(), orderItem.getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

}
