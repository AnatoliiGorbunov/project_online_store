package ru.geekbrains.auth_service.dto;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;

}
