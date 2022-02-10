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
    public void userEnterHisAgeIs(int age) {
        checkoutPage.setAge(String.format("%s",age));
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

    @Then("alert is not active")
    public void alertIsNotActive() {
        orderPage.getAlertDiv().shouldBe(hidden);
    }

    @And("order is confirmed")
    public void orderIsConfirmed() {
        orderPage.getSentMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @When("user adds {int} cola")
    public void userAddsNCola(int n) {
        for(int i=0;i<n;i++){
            cartPage.addCola();
        }
    }

    @When("user adds {int} beer")
    public void userAddsNBeer(int n) {
        for(int i=0;i<n;i++){
            cartPage.addBeer();
        }
    }

    @When("user adds {int} wine")
    public void userAddsNWine(int n) {
        for(int i=0;i<n;i++){
            cartPage.addBeer();
        }
    }

    @When("user adds {int} beers")
    public void userAddsNBeers(int n) {
        for(int i=0; i<n; ++i) {
            cartPage.addBeer();
        }
    }

    @When("user adds {int} wines")
    public void userAddsNWines(int n) {
        for(int i=0; i<n; ++i) {
            cartPage.addWine();
        }
    }

    @When("user adds {int} cola {int} beer {int} wine")
    public void userAddsColaBeerWine(int cola, int beer, int wine) {
        userAddsNCola(cola);
        userAddsNBeers(beer);
        userAddsNWines(wine);
    }
}
