package ru.yandex.praktikum.stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.dto.UserRequest;
import ru.yandex.praktikum.stellarburgers.BurgersAccountProfilePage;
import ru.yandex.praktikum.stellarburgers.BurgersLoginPage;
import ru.yandex.praktikum.stellarburgers.BurgersMainPage;

import static ru.yandex.praktikum.generator.UserRequestGenerator.getNewUserRequest;

public class CheckGoToPageTest extends BaseUITest {

    @Test
    @DisplayName("Тест на переход по клику в «Личный кабинет»")
    public void goToLogInPageTest() {

        UserRequest userRequest = registerNewCustomer();

        logInCustomer(userRequest);

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        clearUserData(userRequest);

        Assert.assertTrue("Переход в личный кабинет по кнопке \"Личный кабинет\" не работает",
                objBurgersMainPage.isAccountProfilePageOpened());
    }

    @Test
    @DisplayName("Тест на переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void clickConstructorButtonTest() {

        UserRequest userRequest = registerNewCustomer();

        logInCustomer(userRequest);

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickConstructorButton();

        clearUserData(userRequest);

        Assert.assertTrue("Переход на главную страницу по кнопке \"Конструктор\" не работает",
                objBurgersAccountProfilePage.isMainPageOpened());

    }

    @Test
    @DisplayName("Тест на переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void clickStellarBurgerLogoTest() {

        UserRequest userRequest = registerNewCustomer();

        logInCustomer(userRequest);

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.clickPersonalAccountButton();

        BurgersAccountProfilePage objBurgersAccountProfilePage = new BurgersAccountProfilePage(driver);
        objBurgersAccountProfilePage.clickStellarBurgersLogo();

        clearUserData(userRequest);

        Assert.assertTrue("Переход на главную страницу при нажатии на логотип \"Stellar Burger\" не работает",
                objBurgersAccountProfilePage.isMainPageOpened());

    }

    private void logInCustomer(UserRequest userRequest) {

        BurgersLoginPage objBurgersLoginPage = new BurgersLoginPage(driver);

        objBurgersLoginPage.openPage();
        objBurgersLoginPage.fillInCustomerData(userRequest);
        objBurgersLoginPage.clickLoginButton();

    }

    private UserRequest registerNewCustomer() {

        UserRequest userRequest = getNewUserRequest(8,6);

        createUser(userRequest);

        return userRequest;
    }

}