package ru.yandex.praktikum.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.dto.UserRequest;

import java.time.Duration;

public class BurgersLoginPage extends BurgersBasePage {

    // кнопка "Войти"
    private final By buttonLogin = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//button[text() = 'Войти']");

    // поле ввода "Email"
    private final By fieldEmail = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[1]//input[@name = 'name']");

    // поле ввода "Пароль"
    private final By fieldPassword = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']//fieldset[2]//input[@name = 'Пароль']");

    public BurgersLoginPage(WebDriver driver) {
        super(driver);
    }

    // открыть страницу
    public void openPage() {
        driver.get(BURGERS_LOGIN_PAGE);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
    }

    public void clickLoginButton() {
        driver.findElement(buttonLogin).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_MAIN_PAGE));
    }

    public void fillInCustomerData(UserRequest userRequest) {
        driver.findElement(fieldEmail).sendKeys(userRequest.getEmail());
        driver.findElement(fieldPassword).sendKeys(userRequest.getPassword());
    }

}