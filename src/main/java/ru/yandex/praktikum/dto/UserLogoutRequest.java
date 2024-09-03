package ru.yandex.praktikum.dto;

import lombok.Data;

@Data
public class UserLogoutRequest {
    private String token;
}