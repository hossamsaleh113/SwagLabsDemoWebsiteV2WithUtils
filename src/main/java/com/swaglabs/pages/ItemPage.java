package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ItemPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By cartButton = By.xpath("//button[contains(@class , 'btn_primary')]");
    private final By backButton = By.id("back-to-products");
    private final By price = By.cssSelector("[data-test='inventory-item-price']");
    private final By itemName = By.cssSelector("[data-test='inventory-item-name']");
    //constructor
    public ItemPage(GUI_Driver driver){
        this.driver =driver;
    }
    //actions
    @Step("Getting button state")
    public String getButtonState(){
        return driver.element().getText(cartButton);
    }

    @Step("Getting item Name")
    public String getItemName(){
        return driver.element().getText(itemName);
    }

    @Step("Getting item price")
    public String getPrice(){
        return driver.element().getText(price);
    }

    @Step("Clicking back to products")
    public HomePage clickBackToProduct(){
        driver.element().click(backButton);
        return new HomePage(driver);
    }
    //validations
    @Step("Assert button state")
    public ItemPage assertButtonText(String expected){
        driver.validations().assertEquals(getButtonState() , expected , "Incorrect button state");
        return this;
    }

    @Step("Assert product details")
    public ItemPage assetProductDetails(String exItemName , String  exPrice){
        driver.verifications().assertEquals(getItemName() ,exItemName , "Incorrect item name" );
        driver.verifications().assertEquals(getPrice() , exPrice ,"Incorrect Item price" );
        return this;
    }



}
