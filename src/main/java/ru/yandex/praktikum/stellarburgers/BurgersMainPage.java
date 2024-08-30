package ru.yandex.praktikum.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BurgersMainPage extends BurgersBasePage {

    // кнопка "Войти в аккаунт"
    private final By buttonLogin = By.xpath(".//section[@class = 'BurgerConstructor_basket__29Cd7 mt-25 ']//button[text() = 'Войти в аккаунт']");

    // кнопка "Личный Кабинет"
    private final By buttonPersonalAccount = By.xpath(".//header[@class = 'AppHeader_header__X9aJA pb-4 pt-4']//*[text() = 'Личный Кабинет']");

    // переключатель "Булки"
    private final By sectionBuns = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div[1]");

    // переключатель "Соусы"
    private final By sectionSouses = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']/div/div[2]");

    // переключатель "Начинки"
    private final By sectionFillings = By.xpath(".//section[@class = 'BurgerIngredients_ingredients__1N8v2']//div/div[3]");

    public BurgersMainPage(WebDriver driver) {
        super(driver);
    }

    // открытие страницы
    public void openPage() {
        driver.manage().window().maximize();
        driver.get(BURGERS_MAIN_PAGE);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
    }

    public void clickLoginButton() {

        driver.findElement(buttonLogin).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));

    }

    public void clickPersonalAccountButton() {

        driver.findElement(buttonPersonalAccount).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_ACCOUNT_PROFILE_PAGE));

    }

    public void clickPersonalAccountButtonToLogin() {

        driver.findElement(buttonPersonalAccount).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlContains(BURGERS_LOGIN_PAGE));

    }

    public boolean clickAndCheckIfBunsSectionChosen() {

        // секция "Булки" активна по умолчанию
        // переход на другую секцию, чтобы сделать ее неактивной
        WebElement souses = driver.findElement(sectionSouses);
        souses.click();

        WebElement buns = driver.findElement(sectionBuns);
        buns.click();

        return checkIfSectionChosen(buns);
    }

    public boolean clickAndCheckIfSousesSectionChosen() {

        WebElement souses = driver.findElement(sectionSouses);
        souses.click();

        return checkIfSectionChosen(souses);
    }

    public boolean clickAndCheckIfFillingsSectionChosen() {

        WebElement fillings = driver.findElement(sectionFillings);
        fillings.click();

        return checkIfSectionChosen(fillings);
    }

    public boolean isMainPageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_MAIN_PAGE);
    }

    public boolean isAccountProfilePageOpened() {
        return driver.getCurrentUrl().contains(BURGERS_ACCOUNT_PROFILE_PAGE);
    }

    private boolean checkIfSectionChosen(WebElement section) {
        // имя класса элемента при переключении на секцию в разделе "Конструктор"
        String CLASS_NAME_WHEN_CONSTRUCTOR_SECTION_IS_CHOSEN = "tab_tab_type_current__2BEPc";
        return Objects.requireNonNull(section.getAttribute("class")).contains(CLASS_NAME_WHEN_CONSTRUCTOR_SECTION_IS_CHOSEN);
    }

}