package ru.yandex.praktikum.stellarburgerstest;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.BurgersMainPage;

public class CheckGoToConstructorSectionsTest extends BaseUITest {

    @Test
    @DisplayName("Тест на переход к разделу \"Булки\"")
    public void goToBunsSectionTest() {

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.openPage();

        Assert.assertTrue("Переход на секцию \"Булки\" не работает",
                objBurgersMainPage.clickAndCheckIfBunsSectionChosen());
    }

    @Test
    @DisplayName("Тест на переход к разделу \"Соусы\"")
    public void goToSousesSectionTest() {

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.openPage();

        Assert.assertTrue("Переход на секцию \"Соусы\" не работает",
                objBurgersMainPage.clickAndCheckIfSousesSectionChosen());
    }

    @Test
    @DisplayName("Тест на переход к разделу \"Начинки\"")
    public void goToFillingsSectionTest() {

        BurgersMainPage objBurgersMainPage = new BurgersMainPage(driver);
        objBurgersMainPage.openPage();

        Assert.assertTrue("Переход на секцию \"Начинки\" не работает",
                objBurgersMainPage.clickAndCheckIfFillingsSectionChosen());

    }

}