package ru.geekbrains.project_online_store.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.project_online_store.dto.OrderItemDto;
import ru.geekbrains.project_online_store.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
