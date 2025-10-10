package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //Locators
    private final GUI_Driver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMassage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(GUI_Driver driver){
        this.driver = driver;
    }

    //navigateToPage
    @Step("Navigating to login page")
    public void navigateToPage(){
          driver.browser().navigateToURL(PropertiesUtils.getPropertyValue("baseURL"));
    }

    //Actions
    @Step("Enter Username: {0}")
    public LoginPage enterUsername(String username){
        driver.element().type( this.usernameField , username );
        return this;
    }

    @Step("Enter password: {0}")
    public LoginPage  enterPassword(String password){
        driver.element().type(this.passwordField , password);
        return this;
    }

    @Step("Clicking login")
    public LoginPage clickLogin(){
        driver.element().click(this.loginButton);
        return this;
    }

    @Step("Get error massage")
    public String getErrorMassage(){
        return driver.element().getText(this.errorMassage);
    }

    @Step("Successfully Login")
    public HomePage successfullLogin(String username , String password){
        enterUsername(username).enterPassword(password).clickLogin();
        return new HomePage(driver);
    }

    //Validations

    @Step("Asserting home page url")
    public LoginPage assertHomePageURL(){
        CustomSoftAssertion.softAssert.assertEquals(driver.browser().getCurrentURL() , PropertiesUtils.getPropertyValue("homeURL") , "Incorrect URL");
        return this;
    }

    @Step("Asserting home page title")
    public LoginPage assertHomePageTitle(){
        CustomSoftAssertion.softAssert.assertEquals(driver.browser().getPageTitle() , PropertiesUtils.getPropertyValue("page_title") ,  "Incorrect Title");
        return this;
    }

    @Step("Asserting soft login")
    public void assertSuccessfulLoginSoft(){
        assertHomePageURL().assertHomePageTitle();
    }

    @Step("Asserting successful login")
    public LoginPage assertSuccessfullLogIn(){
        Validation.validateEquals(driver.browser().getCurrentURL() , PropertiesUtils.getPropertyValue("homeURL") ,
                "Incorrect Page URL");
        return this;
    }


    @Step("Asserting unsuccessful login")
    public LoginPage assertUnSuccessfulLogin(){
        Validation.validateEquals(getErrorMassage() , PropertiesUtils.getPropertyValue("errorMassage"),
                "Incorrect Error Massage");
        return this;
    }
}
