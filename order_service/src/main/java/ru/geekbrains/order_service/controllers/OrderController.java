package ru.geekbrains.order_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.order_service.converters.OrderConverter;
import ru.geekbrains.order_service.dto.OrderDetailsDto;
import ru.geekbrains.order_service.dto.OrderDto;
import ru.geekbrains.order_service.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;



    @GetMapping
    public List<OrderDto> getCurrenOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
