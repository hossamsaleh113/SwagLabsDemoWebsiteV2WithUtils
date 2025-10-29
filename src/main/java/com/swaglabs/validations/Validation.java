package com.swaglabs.validations;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation extends BaseAssertion {
    private WebDriver driver;
    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Validating that {0} is true ")
    public void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }


    @Override
    @Step("Validating that {0} is false ")
    public void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }


    @Override
    @Step("Validating that {expected} equals {actual}")
    public void assertEquals(String expected, String actual, String massage) {
        Assert.assertEquals(actual, expected, massage);
    }

    @Override
    @Step("Validating that {expected} not-equals {actual}")
    public void assertNotEquals(String expected, String actual, String massage) {
        Assert.assertNotEquals(actual, expected, massage);
    }

    @Override
    @Step("Validating that current url equals {expected}")
    public void assertPageURL(String expected) {
        Assert.assertEquals(driver.getCurrentUrl(), expected);
    }

    @Override
    @Step("Validate that current page title equals {expected}")
    public void assertPageTitle(String expected) {
        Assert.assertEquals(driver.getCurrentUrl(), expected);
    }
}
