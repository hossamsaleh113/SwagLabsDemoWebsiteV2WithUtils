package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.LogsUtils;
import com.swaglabs.utils.Validation;
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
        LogsUtils.info("Adding" , itemName , "to Cart");
        By locator = RelativeLocator.with(button).below(By.xpath("//div[contains(text(), '" + itemName +"')]"));
        driver.element().click(locator);
        return this;
    }

    @Step("Click cart icon")
    public CartPage clickCartIcon(){
        LogsUtils.info("Clicking Cart Icon");
        driver.element().click(cartIcon);
        return new CartPage(driver);
    }

    @Step("Click on item name: {itemName}")
    public ItemPage clickItemName(String itemName){
        LogsUtils.info("Clicking item name");
        By locator = By.linkText(itemName);
        driver.element().click(locator);
        return new ItemPage(driver);
    }

    @Step("Click on item image: {itemName}")
    public ItemPage clickItemIcon(String itemName){
        LogsUtils.info("Clicking item icon");
        By locator = RelativeLocator.with(itemImg).toLeftOf(By
                .xpath("//div[contains(text() , '" + itemName + "')]"));
        driver.element().click(locator);
        return new ItemPage(driver);

    }


    //validations

    @Step("Asserting adding item to cart")
    public HomePage assertItemAddedToCart(String itemName){

        By locator = RelativeLocator.with(button).below(By.xpath("//div[contains(text(), '" + itemName +"')]"));
        Validation.validateTrue(driver.element().getText(locator).contains("Remove") ,
                "Incorrect Adding");
        LogsUtils.info( itemName,"added to cart successfully");
        return this;
    }

    @Step("Asserting Cart icon number")
    public HomePage assertCartIconNumber(String number){
        Validation.validateEquals(driver.element().getCartIconNumber() , number , "Incorrect cart icon number");
        LogsUtils.info("Number" , number , "is correctly shown on cart icon");
        return this;
    }
}
