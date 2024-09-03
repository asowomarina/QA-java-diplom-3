package ru.yandex.praktikum.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
    private String refreshToken;
    private String authorization;
}