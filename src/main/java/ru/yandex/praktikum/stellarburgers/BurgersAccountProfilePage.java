package ru.yandex.praktikum.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BurgersAccountProfilePage extends BurgersBasePage {

    // кнопка "Выход"
    private final By buttonLogout = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']//button[text() = 'Выход']");

    // кнопка "Конструктор"
    private final By buttonConstructor = By.xpath(".//*[@class = 'AppHeader_header__link__3D_hX']//*[text() = 'Конструктор']");

    // логотип "Stellar Burgers"
    private final By logoStellarBurgers = By.xpath(".//*[@class = 'AppHeader_header__X9aJA pb-4 pt-4']//*[@class = 'AppHeader_header__logo__2D0X2']");

    public BurgersAccountProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogoutButton() {

        driver.findElement(buttonLogout).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));

    }

    public void clickConstructorButton() {

        driver.findElement(buttonConstructor).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_MAIN_PAGE));

    }

    public void clickStellarBurgersLogo() {

        driver.findElement(logoStellarBurgers).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_MAIN_PAGE));

    }

    public boolean isMainPageOpened() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains(BURGERS_MAIN_PAGE);
    }

    public boolean isLoginPageOpened() {
        return Objects.requireNonNull(driver.getCurrentUrl()).contains(BURGERS_LOGIN_PAGE);
    }

}