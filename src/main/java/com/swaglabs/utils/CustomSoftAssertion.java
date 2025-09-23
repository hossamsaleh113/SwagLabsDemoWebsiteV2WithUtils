package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.logging.Logs;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssertion extends SoftAssert {
    public static CustomSoftAssertion softAssert = new CustomSoftAssertion();

    public static void customAssertAll(ITestResult result){
        try {
            softAssert.assertAll("Custom Soft Assertion");
        }catch (AssertionError e){
            LogsUtils.error("Assertion failure" + e.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);
        }finally {
            reinitializeSoftAssert();
        }
    }

    private static void reinitializeSoftAssert(){
        softAssert = new CustomSoftAssertion();
    }
}
