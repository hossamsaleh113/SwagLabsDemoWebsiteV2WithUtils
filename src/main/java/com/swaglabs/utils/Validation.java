package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Validation {
    private Validation() {
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
    public static void validatePageURL(WebDriver driver, String expected) {
        Assert.assertEquals(BrowserActions.getCurrentURL(driver), expected);
    }

    @Step("Validate title")
    public static void validatePageTitle(WebDriver driver, String expected) {
        Assert.assertEquals(BrowserActions.getPageTitle(driver), expected);
    }
}
