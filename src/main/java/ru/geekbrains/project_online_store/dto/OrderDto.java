package ru.geekbrains.project_online_store.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> itemDtoList;
    private Integer totalPrice;
    private String address;
    private String phone;
}
