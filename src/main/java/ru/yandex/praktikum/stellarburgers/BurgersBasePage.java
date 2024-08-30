package ru.yandex.praktikum.stellarburgers;

import org.openqa.selenium.WebDriver;

public abstract class BurgersBasePage {

    protected final WebDriver driver;

    // URL's страниц сервиса
    public static final String BURGERS_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    public static final String BURGERS_REGISTER_PAGE = "https://stellarburgers.nomoreparties.site/register";
    public static final String BURGERS_LOGIN_PAGE = "https://stellarburgers.nomoreparties.site/login";
    public static final String BURGERS_ACCOUNT_PROFILE_PAGE = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final String BURGERS_FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";

    public BurgersBasePage(WebDriver driver) {
        this.driver = driver;
    }

}