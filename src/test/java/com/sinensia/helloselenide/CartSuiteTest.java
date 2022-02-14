package com.sinensia.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class CartSuiteTest {
    CartPage cartPage =  new CartPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void setUp() {
        open("/");
    }

    @Test
    public void colaTest() {
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
    }
    @Test
    public void beerTest() {
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
    }
    @Test
    public void wineTest() {
        cartPage.addWine();
        cartPage.total().shouldBe(text("€3.00"));
        cartPage.addWine();
        cartPage.total().shouldBe(text("€6.00"));
    }
}
