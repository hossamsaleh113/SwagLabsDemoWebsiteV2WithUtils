package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.LogsUtils;
import com.swaglabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


    //actions
    @Step("Adding Item to cart")
    public HomePage addSpecificItemToCart(String itemName){
        LogsUtils.info("Adding" , itemName , "to Cart");
        By locator = RelativeLocator.with(button).below(By.xpath("//div[contains(text(), '" + itemName +"')]"));
        driver.element().click(locator);
        return this;
    }

    @Step("Clicking cart icon")
    public CartPage clickCartIcon(){
        LogsUtils.info("Clicking Cart Icon");
        driver.element().click(cartIcon);
        return new CartPage(driver);
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
}
