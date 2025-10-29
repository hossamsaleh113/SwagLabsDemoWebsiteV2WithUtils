package com.swaglabs.validations;

import com.swaglabs.utils.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class SoftAssertion extends BaseAssertion {
    public static SoftAssert softAssert = new SoftAssert();

    public SoftAssertion(WebDriver driver) {
        super(driver);
    }

    public static void assertAll(ITestResult result){
        try {
            softAssert.assertAll("Custom Soft Assertion");
        }catch (AssertionError e){
            LogsManager.error("Assertion failure" + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }finally {
            reinitializeSoftAssert();
        }
    }

    private static void reinitializeSoftAssert(){
        softAssert = new SoftAssert();
    }


    @Override
    @Step("Validating that {0} is true ")
    public void assertTrue(boolean condition) {
        softAssert.assertTrue(condition);
    }

    @Override
    @Step("Validating that {0} is false ")
    public void assertFalse(boolean condition) {
        softAssert.assertFalse(condition);
    }

    @Override
    @Step("Validating that {expected} equals {actual}")
    public void assertEquals(String actual , String expected , String massage) {
        softAssert.assertEquals(actual , expected , massage);
    }

    @Override
    @Step("Validating that {expected} not-equals {actual}")
    public void assertNotEquals(String expected, String actual, String massage) {
        softAssert.assertNotEquals(actual , expected , massage);
    }

    @Override
    @Step("Validating that current url equals {expected}")
    public void assertPageURL(String expected) {
        softAssert.assertEquals(driver.getCurrentUrl() , expected);
    }

    @Override
    @Step("Validate that current page title equals {expected}")
    public void assertPageTitle(String expected) {
        softAssert.assertEquals(driver.getTitle() , expected);
    }
}
