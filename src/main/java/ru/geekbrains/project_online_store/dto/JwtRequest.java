package ru.geekbrains.project_online_store.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
