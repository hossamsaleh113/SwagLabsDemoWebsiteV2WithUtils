package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By confirmationMassage = By.cssSelector("[data-test=complete-header]");
    //constructor
    public ConfirmationPage(GUI_Driver driver){
        this.driver = driver;
    }
    //actions
    @Step("Getting confirmation massage")
    public String getConfirmationMassage(){
        return driver.element().getText(confirmationMassage);
    }
    //validations
    @Step("Assert confirmation massage: {0}")
    public ConfirmationPage assertConfirmationMassage(String expected){
        Validation.validateEquals(getConfirmationMassage() , expected , "Incorrect massage");
        return this;
    }
}
