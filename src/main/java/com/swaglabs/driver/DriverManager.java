package com.swaglabs.driver;

import com.swaglabs.utils.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager(){
        super();
    }

@Step("Create Driver instance on:  {browserName}")
    public static WebDriver createInstance(String browserName){
        WebDriver driver = BrowserFactory.getBrowser(browserName);
        LogsUtils.info("Driver created on browser" , browserName.toUpperCase());
        setDriver(driver);
        return getDriver();
    }

    public static WebDriver getDriver(){
        if(driverThreadLocal.get() == null){
            LogsUtils.error("Driver is null");
            fail("Driver is null");
        }
            return driverThreadLocal.get();
    }



    public static void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }





}
