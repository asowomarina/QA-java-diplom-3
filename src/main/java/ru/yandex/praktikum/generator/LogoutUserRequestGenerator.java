package ru.yandex.praktikum.generator;

import ru.yandex.praktikum.dto.UserLoginRequest;
import ru.yandex.praktikum.dto.UserLogoutRequest;

public class LogoutUserRequestGenerator {

    public static UserLogoutRequest from (UserLoginRequest userLoginRequest) {

        UserLogoutRequest userLogoutRequest = new UserLogoutRequest();
        userLogoutRequest.setToken(userLoginRequest.getRefreshToken());

        return userLogoutRequest;
    }
}