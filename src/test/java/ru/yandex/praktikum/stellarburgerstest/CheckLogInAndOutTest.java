package ru.yandex.praktikum.stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.dto.UserRequest;
import ru.yandex.praktikum.stellarburgers.*;

import static ru.yandex.praktikum.generator.UserRequestGenerator.getNewUserRequest;

public class CheckLogInAndOutTest extends BaseUITest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void logInViaButtonLogInAccountSuccessfulTest() {

        UserRequest userRequest = registerNewCustomer();

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);

        objBurgersMainPage.openPage();
        objBurgersMainPage.clickLoginButton();

        logInCustomer(userRequest);

        // если открыта главная страница, пользователь залогинился успешно
        boolean isMainPageOpened = objBurgersMainPage.isMainPageOpened();

        if (isMainPageOpened) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Ошибка при входе пользователя по кнопке «Войти в аккаунт» на главной странице",
                isMainPageOpened);

    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void logInViaButtonPersonalAccountSuccessfulTest() {

        UserRequest userRequest = registerNewCustomer();

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);

        objBurgersMainPage.openPage();
        objBurgersMainPage.clickPersonalAccountButtonToLogin();

        logInCustomer(userRequest);

        // если открыта главная страница, пользователь залогинился успешно
        boolean isMainPageOpened = objBurgersMainPage.isMainPageOpened();

        if (isMainPageOpened) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Ошибка при входе пользователя через кнопку «Личный кабинет»",
                isMainPageOpened);

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void logInViaRegistrationPageSuccessfulTest() {

        UserRequest userRequest = registerNewCustomer();

        BurgersRegisterPage objBurgersRegisterPage = new BurgersRegisterPage(driver);

        objBurgersRegisterPage.openPage();
        objBurgersRegisterPage.clickLogInButton();

        logInCustomer(userRequest);

        // если открыта главная страница, пользователь залогинился успешно
        boolean isMainPageOpened = objBurgersRegisterPage.isMainPageOpened();

        if (isMainPageOpened) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Ошибка при входе пользователя через кнопку в форме регистрации",
                isMainPageOpened);

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void logInViaForgotPasswordPageSuccessfulTest() {

        UserRequest userRequest = registerNewCustomer();

        BurgersForgotPasswordPage objBurgersForgotPasswordPage = new BurgersForgotPasswordPage(driver);

        objBurgersForgotPasswordPage.openPage();
        objBurgersForgotPasswordPage.clickLogInButton();

        logInCustomer(userRequest);

        // если открыта главная страница, пользователь залогинился успешно
        boolean isMainPageOpened = objBurgersForgotPasswordPage.isMainPageOpened();

        if (isMainPageOpened) {
            clearUserData(userRequest);
        }

        Assert.assertTrue("Ошибка при входе пользователя через кнопку в форме восстановления пароля",
                isMainPageOpened);

    }

    @Test
    @DisplayName("Тест на выход по кнопке «Выйти» в личном кабинете")
    public void logOutCustomerTest() {

        UserRequest userRequest = registerNewCustomer();

        logInCustomer(userRequest);

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickLogoutButton();

        clearUserData(userRequest);

        Assert.assertTrue("Выход по кнопке «Выйти» в личном кабинете не работает",
                objBurgersAccountProfilePage.isLoginPageOpened());

    }

    private UserRequest registerNewCustomer() {

        UserRequest userRequest = getNewUserRequest(8,6);

        createUser(userRequest);

        return userRequest;
    }

    private void logInCustomer(UserRequest userRequest) {

        BurgersLoginPage objBurgersLoginPage = new BurgersLoginPage(driver);

        objBurgersLoginPage.openPage();
        objBurgersLoginPage.fillInCustomerData(userRequest);
        objBurgersLoginPage.clickLoginButton();

    }

}