package com.swaglabs.tests;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P01_LoginPage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class HomePageTests {
    private GUI_Driver driver;
    private JsonUtils testData;

    @Test
    public void testClickingOnItemName(){
        new P01_LoginPage(driver).successfulLogin().clickItemIcon(testData.getJsonData("products-list.item1.name"));
    }


    @BeforeClass
    public void setUP(){
        testData = new JsonUtils("test-data");
        driver = new GUI_Driver("chrome");
        new P01_LoginPage(driver).navigateToPage();
    }

    @AfterClass
    public void tearDown(){
        driver.browser().closeBrowser();
    }
}
