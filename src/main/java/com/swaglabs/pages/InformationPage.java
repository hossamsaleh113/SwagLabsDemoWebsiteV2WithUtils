package com.swaglabs.pages;

import com.swaglabs.utils.CustomSoftAssertion;
import com.swaglabs.utils.ElementActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.naming.ldap.PagedResultsControl;

public class InformationPage {
    //variables
    private WebDriver driver;
    //locators
    private final By fNameField = By.id("first-name");
    private final By lNameField = By.id("last-name");
    private final By zipField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    //constructor
    public InformationPage(WebDriver driver){
        this.driver = driver;
    }
    //actions
    @Step("enter first name {0} , last name {1} and postal code {2}")
    public InformationPage fillInformation(String firstName , String lastName , String zip){
        ElementActions.sendData(driver , fNameField , firstName);
        ElementActions.sendData(driver , lNameField , lastName);
        ElementActions.sendData(driver , zipField , zip);
        return this;
    }

    @Step("Click on continue button")
    public OverviewPage clickContinue(){
        ElementActions.clickElement(driver , continueButton);
        return new OverviewPage(driver);
    }

    @Step("Click on cancel button")
    public CartPage clickCancel(){
        ElementActions.clickElement(driver , cancelButton);
        return new CartPage(driver);
    }

    //validations
    public InformationPage assertInputInformation(String fName , String lName , String zip){
        CustomSoftAssertion.softAssert.assertEquals(ElementActions.getTextFromInputField(driver , fNameField) , fName);
        CustomSoftAssertion.softAssert.assertEquals(ElementActions.getTextFromInputField(driver , lNameField) , lName);
        CustomSoftAssertion.softAssert.assertEquals(ElementActions.getTextFromInputField(driver , zipField) , zip);


        return this;
    }
}
