package com.sinensia.helloselenide.cucumber;

import com.sinensia.helloselenide.CartPage;
import com.sinensia.helloselenide.CheckoutPage;
import com.sinensia.helloselenide.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class RobobarStepDefinitions {
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderPage orderPage;

    @Given("user opens robobar webside")
    public void userOpensRobobarWebside() {
        open("http://localhost:3000");
        cartPage = new CartPage();
    }

    @When("user adds a cola")
    public void userAddACola() {
        cartPage.addCola();
    }

    @Then("total should be €{double}")
    public void totalShouldBe€(Double total) {
        cartPage.total().shouldBe(exactText(String.format("€%.2f",total)));
    }

    @When("user adds a beer")
    public void userAddsABeer() {
        cartPage.addBeer();
    }

    @When("user adds a wine")
    public void userAddsAWine() {
        cartPage.addWine();
    }

    @When("user press submit button")
    public void userPressSubmitButton() {
        cartPage.checkout();
        checkoutPage = new CheckoutPage();
    }

    @When("user enter his age is {int}")
    public void userEnterHisAgeIs(int arg0) {
        checkoutPage.setAge("17");
    }

    @When("user press order button")
    public void userPressOrderButton() {
        checkoutPage.order();
        orderPage = new OrderPage();
    }

    @Then("alert is active")
    public void alertIsActive() {
        orderPage.getAlertDiv().shouldNotBe(hidden);
    }
}
