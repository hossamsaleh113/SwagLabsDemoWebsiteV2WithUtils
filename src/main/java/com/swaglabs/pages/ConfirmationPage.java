package com.swaglabs.pages;

import com.swaglabs.utils.ElementActions;
import com.swaglabs.utils.Validation;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    //variables
    private WebDriver driver;
    //locators
    private final By confirmationMassage = By.cssSelector("[data-test=complete-header]");
    //constructor
    public ConfirmationPage(WebDriver driver){
        this.driver = driver;
    }
    //actions
    @Step("Getting confirmation massage")
    public String getConfirmationMassage(){
        return ElementActions.getText(driver , confirmationMassage);
    }
    //validations
    @Step("Assert confirmation massage: {0}")
    public ConfirmationPage assertConfirmationMassage(String expected){
        Validation.validateEquals(getConfirmationMassage() , expected , "Incorrect massage");
        return this;
    }
}
