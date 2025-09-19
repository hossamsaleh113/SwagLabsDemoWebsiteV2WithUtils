package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //variables
    private WebDriver driver;
    //locators
    private final By checkoutButton = By.id("checkout");
    //constructor
    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    //actions
    private By productName(String productName){
        String name = "//div[@class='inventory_item_name'][text() = '" + productName + "']";
        return By.xpath(name);
    }

    private By productPrice(String productName){
        String price = "//div[@class='inventory_item_name'][text() = '" + productName + "']//..//..//div[@class='inventory_item_price']";
        return By.xpath(price);
    }

    @Step("Clicking checkout button")
    public InformationPage clickCheckout(){
        ElementActions.clickElement(driver , checkoutButton);
        return new InformationPage(driver);
    }

    //validations
    @Step("Asserting product details on cart page")
    public void assertProductDetails(String productName , String productPrice){
        CustomSoftAssertion.softAssert.assertEquals(ElementActions.getText(driver , productName(productName)) , productName ,
                "Product name mismatch");
        CustomSoftAssertion.softAssert.assertEquals(ElementActions.getText(driver , productPrice(productName)) , productPrice ,
                "Product price mismatch");
    }
}
