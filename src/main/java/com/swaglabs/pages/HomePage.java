package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.PropertiesUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class HomePage {
    //variables
    private final GUI_Driver driver;

    //constructor
    public HomePage(GUI_Driver driver){
        this.driver = driver;
    }

    //locators
    private final By button = By.tagName("button");
    private final By cartIcon = By.cssSelector("[data-test=shopping-cart-link]");
    private final By itemImg = By.className("inventory_item_img");


    //actions
    @Step("Add Item to cart: {itemName}")
    public HomePage addSpecificItemToCart(String itemName){
        LogsManager.info("Adding" , itemName , "to Cart");
        By locator = RelativeLocator.with(button).below(By.xpath("//div[contains(text(), '" + itemName +"')]"));
        driver.element().click(locator);
        return this;
    }

    @Step("Click cart icon")
    public CartPage clickCartIcon(){
        LogsManager.info("Clicking Cart Icon");
        driver.element().click(cartIcon);
        return new CartPage(driver);
    }

    @Step("Click on item name: {itemName}")
    public ItemPage clickItemName(String itemName){
        LogsManager.info("Clicking item name");
        By locator = By.linkText(itemName);
        driver.element().click(locator);
        return new ItemPage(driver);
    }

    @Step("Click on item image: {itemName}")
    public ItemPage clickItemIcon(String itemName){
        LogsManager.info("Clicking item icon");
        By locator = RelativeLocator.with(itemImg).toLeftOf(By
                .xpath("//div[contains(text() , '" + itemName + "')]"));
        driver.element().click(locator);
        return new ItemPage(driver);


    }



    //validations

    @Step("Asserting home page url")
    public HomePage assertHomePageURL() {
        driver.verifications().assertPageURL(PropertiesUtils.getProperty("homeURL"));
        return this;
    }

    @Step("Asserting home page title")
    public HomePage assertHomePageTitle() {
       driver.verifications().assertPageTitle(PropertiesUtils.getProperty("page_title"));
        return this;
    }

    @Step("Asserting soft login")
    public void assertSuccessfulLoginSoft() {
        assertHomePageURL().assertHomePageTitle();
    }

    @Step("Asserting adding item to cart")
    public HomePage assertItemAddedToCart(String itemName){

        By locator = RelativeLocator.with(button).below(By.xpath("//div[contains(text(), '" + itemName +"')]"));
        driver.validations().assertTrue(driver.element().getText(locator).contains("Remove"));
        LogsManager.info( itemName,"added to cart successfully");
        return this;
    }

    @Step("Asserting Cart icon number")
    public HomePage assertCartIconNumber(String number){
        driver.validations().assertEquals(driver.element().getCartIconNumber() , number , "Incorrect cart icon number");
        LogsManager.info("Number" , number , "is correctly shown on cart icon");
        return this;
    }
}
