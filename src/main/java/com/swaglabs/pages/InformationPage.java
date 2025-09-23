package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InformationPage {
    //variables
    private GUI_Driver driver;
    //locators
    private final By fNameField = By.id("first-name");
    private final By lNameField = By.id("last-name");
    private final By zipField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    //constructor
    public InformationPage(GUI_Driver driver) {
        this.driver = driver;
    }

    //actions
    @Step("enter first name {0} , last name {1} and postal code {2}")
    public InformationPage fillInformation(String firstName, String lastName, String zip) {
        driver.element().type(fNameField, firstName);
        driver.element().type(lNameField, lastName);
        driver.element().type(zipField, zip);
        return this;
    }

    @Step("Click on continue button")
    public OverviewPage clickContinue() {
        driver.element().click( continueButton);
        return new OverviewPage(driver);
    }

    @Step("Click on cancel button")
    public CartPage clickCancel() {
        driver.element().click( cancelButton);
        return new CartPage(driver);
    }

    //validations
    public InformationPage assertInputInformation(String fName, String lName, String zip) {
        CustomSoftAssertion.softAssert.assertEquals(driver.element().getTextFromInputField( fNameField), fName);
        CustomSoftAssertion.softAssert.assertEquals(driver.element().getTextFromInputField(lNameField), lName);
        CustomSoftAssertion.softAssert.assertEquals(driver.element().getTextFromInputField( zipField), zip);


        return this;
    }
}
