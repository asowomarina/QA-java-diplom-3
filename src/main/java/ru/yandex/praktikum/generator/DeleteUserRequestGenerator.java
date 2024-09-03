package ru.yandex.praktikum.generator;

import ru.yandex.praktikum.dto.UserDeleteRequest;
import ru.yandex.praktikum.dto.UserLoginRequest;

public class DeleteUserRequestGenerator {

    public static UserDeleteRequest from (UserLoginRequest userLoginRequest) {

        UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
        userDeleteRequest.setAuthorization(userLoginRequest.getAuthorization());

        return userDeleteRequest;
    }
}