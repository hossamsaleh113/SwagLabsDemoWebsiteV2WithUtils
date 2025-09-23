package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By checkoutButton = By.id("checkout");
    //constructor
    public CartPage(GUI_Driver driver){
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
        driver.element().click(checkoutButton);
        return new InformationPage(driver);
    }

    //validations
    @Step("Asserting product details on cart page")
    public void assertProductDetails(String productName , String productPrice){
        CustomSoftAssertion.softAssert.assertEquals(driver.element().getText( productName(productName)) , productName ,
                "Product name mismatch");
        CustomSoftAssertion.softAssert.assertEquals(driver.element().getText(productPrice(productName)) , productPrice ,
                "Product price mismatch");
    }
}
