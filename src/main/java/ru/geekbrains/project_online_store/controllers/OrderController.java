package ru.geekbrains.project_online_store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.project_online_store.converters.OrderConverter;
import ru.geekbrains.project_online_store.dto.OrderDetailsDto;
import ru.geekbrains.project_online_store.dto.OrderDto;
import ru.geekbrains.project_online_store.entities.User;
import ru.geekbrains.project_online_store.exceptions.ResourceNotFoundException;
import ru.geekbrains.project_online_store.services.OrderService;
import ru.geekbrains.project_online_store.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @PostMapping("/{cartName}")
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto, @PathVariable String cartName){
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user, orderDetailsDto, cartName);
    }

    @GetMapping
    public List<OrderDto> getCurrenUrders(Principal principal){
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
