package com.sinensia.helloselenide;// Generated by Selenium IDE

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgeSuiteTest {
    CartPage cartPage =  new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    OrderPage orderPage = new OrderPage();


    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("/");
    }

    @Test
    public void underageCola() {
        cartPage.getCheckout().shouldBe(disabled);
        cartPage.addCola();
        cartPage.total().shouldBe(text("€1.25"));
        cartPage.getCheckout().shouldBe(enabled);
        cartPage.addCola();
        cartPage.total().shouldBe(text("€2.50"));
        cartPage.checkout();
        checkoutPage.getAgeInput().shouldNot(exist);
        checkoutPage.getOrder().shouldBe(enabled);
        checkoutPage.order();
        orderPage.getSentMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void underageBeer() {
        cartPage.getCheckout().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.getCheckout().shouldBe(enabled);
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
        cartPage.checkout();
        checkoutPage.getOrder().shouldBe(disabled);
        checkoutPage.getAgeInput().should(exist);
        checkoutPage.setAge("17");
        checkoutPage.getOrder().shouldBe(enabled);
        checkoutPage.order();
        orderPage.getAlertDiv().shouldNotBe(hidden);
    }

   // @Test
    public void adultBeer() {
        cartPage.getCheckout().shouldBe(disabled);
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€2.00"));
        cartPage.getCheckout().shouldBe(enabled);
        cartPage.addBeer();
        cartPage.total().shouldBe(text("€4.00"));
        cartPage.checkout();
        checkoutPage.getOrder().shouldBe(disabled);
        checkoutPage.getAgeInput().should(exist);
        checkoutPage.setAge("19");
        checkoutPage.getOrder().shouldBe(enabled);
        checkoutPage.order();
        orderPage.getAlertDiv().shouldBe(hidden);
        orderPage.getSentMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }
}