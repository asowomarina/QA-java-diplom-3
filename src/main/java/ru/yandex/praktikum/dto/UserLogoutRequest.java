package ru.yandex.praktikum.dto;

public class UserLogoutRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}