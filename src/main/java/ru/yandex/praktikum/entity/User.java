package ru.yandex.praktikum.entity;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
}