package com.swaglabs.tests;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class LoginTests {
    private GUI_Driver driver;

    @Severity(SeverityLevel.CRITICAL)
    @Step("Test successful login")
    @Test
    public void testSuccessfulLogin(){
        new LoginPage(driver).successfulLogin().assertSuccessfulLoginSoft();
    }


    @BeforeClass
    public void setUp(){
        driver = new GUI_Driver("chrome");
        new LoginPage(driver).navigateToPage();
    }

    @AfterClass
    public void tearDown(){
        driver.browser().closeBrowser();
    }

}
