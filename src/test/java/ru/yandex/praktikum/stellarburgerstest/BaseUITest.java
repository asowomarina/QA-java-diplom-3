package ru.yandex.praktikum.stellarburgerstest;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.config.WebDriverFactory;
import ru.yandex.praktikum.dto.UserDeleteRequest;
import ru.yandex.praktikum.dto.UserLoginRequest;
import ru.yandex.praktikum.dto.UserLogoutRequest;
import ru.yandex.praktikum.dto.UserRequest;
import ru.yandex.praktikum.generator.DeleteUserRequestGenerator;
import ru.yandex.praktikum.generator.LoginUserRequestGenerator;
import ru.yandex.praktikum.generator.LogoutUserRequestGenerator;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BaseUITest {

    protected static WebDriver driver;

    private UserClient userClient;
    private UserRequest userRequest;
    private UserLoginRequest userLoginRequest;

    @BeforeClass
    public static void startUp() {
        driver = WebDriverFactory.getWebDriver();
    }

    @Before
    public void setUp() {
        userClient = new UserClient();
    }

    @After
    public void clearData() {
        driver.manage().deleteAllCookies();
        clearSessionStorage();
        clearLocalStorage();
    }

    private void clearSessionStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.sessionStorage.clear();");
    }

    private void clearLocalStorage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.localStorage.clear();");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    public void clearUserData(UserRequest userRequest) {

        this.userRequest = userRequest;

        logInUser();
        logOutUser();
        deleteUser();

    }

    public void createUser(UserRequest userRequest) {

        this.userRequest = userRequest;

        userClient.createUser(userRequest)
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("success", equalTo(true))
                .and()
                .body("accessToken", notNullValue());

    }

    public void logInUser() {

        if (userRequest != null) {

            userLoginRequest = LoginUserRequestGenerator.from(userRequest);

            // проверка, что успешно зашли под созданной учетной записью
            ValidatableResponse response = userClient.loginUser(userLoginRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("accessToken", notNullValue());

            // сохранение токенов для дальнейшего выхода и удаления пользователя
            userLoginRequest.setRefreshToken(response.extract().path("refreshToken"));
            userLoginRequest.setAuthorization(response.extract().path("accessToken"));

        }

    }

    public void logOutUser() {

        if (userLoginRequest.getRefreshToken() != null) {

            UserLogoutRequest userLogoutRequest = LogoutUserRequestGenerator.from(userLoginRequest);

            // разлогирование
            userClient.logoutUser(userLogoutRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("message", equalTo("Successful logout"));

        }

    }

    public void deleteUser() {

        if (userLoginRequest.getAuthorization() != null) {

            UserDeleteRequest userDeleteRequest = DeleteUserRequestGenerator.from(userLoginRequest);

            userClient.deleteUser(userDeleteRequest)
                    .log().all()
                    .assertThat()
                    .statusCode(SC_ACCEPTED)
                    .and()
                    .body("success", equalTo(true))
                    .and()
                    .body("message", equalTo("User successfully removed"));

        }
    }

}