package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class P06_OverviewPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By finishButton = By.id("finish");
    //constructor
    public P06_OverviewPage(GUI_Driver driver){
        this.driver = driver;
    }
    //actions
    @Step("Click on finish button")
    public P07_ConfirmationPage clickOnFinishButton(){
        driver.element().click( finishButton);
        return new P07_ConfirmationPage(driver);
    }
    //validations
}
