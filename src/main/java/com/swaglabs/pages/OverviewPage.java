package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By finishButton = By.id("finish");
    //constructor
    public OverviewPage(GUI_Driver driver){
        this.driver = driver;
    }
    //actions
    @Step("Click on finish button")
    public ConfirmationPage clickOnFinishButton(){
        driver.element().click( finishButton);
        return new ConfirmationPage(driver);
    }
    //validations
}
