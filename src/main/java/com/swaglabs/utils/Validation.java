package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {
    private WebDriver driver;
    public Validation(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Validate true")
    public static void validateTrue(Boolean condition, String massage) {
        Assert.assertTrue(condition, massage);
    }
    @Step("Validate false")
    public static void validateFalse(Boolean condition, String massage) {
        Assert.assertFalse(condition, massage);
    }

    @Step("Validate equals")
    public static void validateEquals(String expected, String actual, String massage) {
        Assert.assertEquals(actual, expected, massage);
    }

    @Step("Validate not-equals")
    public static void validateNotEquals(String expected, String actual, String massage) {
        Assert.assertNotEquals(actual, expected, massage);
    }

    @Step("Validate url")
    public void validatePageURL(String expected) {
        Assert.assertEquals(driver.getCurrentUrl(), expected);
    }

    @Step("Validate title")
    public void validatePageTitle(String expected) {
        Assert.assertEquals(driver.getCurrentUrl(), expected);
    }
}
