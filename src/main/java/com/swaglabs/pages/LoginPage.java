package com.swaglabs.pages;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage {

    //Variable
    private final GUI_Driver driver;
    private JsonUtils jasonData = new JsonUtils("test-data");


    //Locators
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMassage = By.cssSelector("[data-test='error']");

    // Constructor
    public LoginPage(GUI_Driver driver) {
        this.driver = driver;
    }

    //navigateToPage
    @Step("Navigating to login page")
    public void navigateToPage() {
        driver.browser().navigateToURL(PropertiesUtils.getProperty("baseUrlWeb"));
    }

    //Actions
    @Step("Enter Username: {0}")
    public LoginPage enterUsername(String username) {
        driver.element().type(this.usernameField, username);
        return this;
    }

    @Step("Enter password: {0}")
    public LoginPage enterPassword(String password) {
        driver.element().type(this.passwordField, password);
        return this;
    }

    @Step("Clicking login")
    public LoginPage clickLogin() {
        driver.element().click(this.loginButton);
        return this;
    }

    @Step("Get error massage")
    public String getErrorMassage() {
        return driver.element().getText(this.errorMassage);
    }

    @Step("Successfully Login")
    public HomePage successfulLogin() {
        enterUsername(jasonData.getJsonData("login-credentials.username")).
                enterPassword(jasonData.getJsonData("login-credentials.password")).clickLogin();
        return new HomePage(driver);
    }

    //Validations

    @Step("Asserting successful login")
    public LoginPage assertSuccessfullLogIn() {
        driver.validations().assertEquals(driver.browser().getCurrentURL(), PropertiesUtils.getProperty("homeURL"),
                "Incorrect Page URL");
        return this;
    }


    @Step("Asserting unsuccessful login")
    public LoginPage assertUnsuccessfulLogin() {
        driver.validations().assertEquals(getErrorMassage(), PropertiesUtils.getProperty("errorMassage"),
                "Incorrect Error Massage");
        return this;
    }
}
