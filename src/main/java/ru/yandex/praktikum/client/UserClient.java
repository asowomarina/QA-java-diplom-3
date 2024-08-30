package ru.yandex.praktikum.client;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.dto.UserDeleteRequest;
import ru.yandex.praktikum.dto.UserLoginRequest;
import ru.yandex.praktikum.dto.UserLogoutRequest;
import ru.yandex.praktikum.dto.UserRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.config.Config.getAPIUserCreate;
import static ru.yandex.praktikum.config.Config.getAPIUserLogin;
import static ru.yandex.praktikum.config.Config.getAPIUserLogout;
import static ru.yandex.praktikum.config.Config.getAPIUserUpdate;

public class UserClient extends RestClient {

    public ValidatableResponse createUser(UserRequest userRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .body(userRequest)
                .post(getAPIUserCreate())
                .then();
    }

    public ValidatableResponse loginUser(UserLoginRequest userLoginRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .body(userLoginRequest)
                .post(getAPIUserLogin())
                .then();
    }

    public ValidatableResponse logoutUser(UserLogoutRequest userLogoutRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .body(userLogoutRequest)
                .post(getAPIUserLogout())
                .then();
    }

    public ValidatableResponse deleteUser(UserDeleteRequest userDeleteRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .header("authorization", userDeleteRequest.getAuthorization())
                .delete(getAPIUserUpdate())
                .then();
    }
}