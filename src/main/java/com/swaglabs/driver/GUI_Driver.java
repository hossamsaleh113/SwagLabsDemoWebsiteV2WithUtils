package com.swaglabs.driver;

import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.ElementActions;
import org.openqa.selenium.WebDriver;


public class GUI_Driver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public GUI_Driver(String browserName){
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);
    }

    private AbstractDriver getDriver(String browserName){
       return switch (browserName.toLowerCase()){
           case "chrome" -> new ChromeFactory();
           case "edge" -> new EdgeFactory();
           case "firefox" -> new FirefoxFactory();
           default -> throw new IllegalArgumentException();
       };
    }



    private void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public static WebDriver get(){
        return driverThreadLocal.get();
    }

    public ElementActions element(){
        return new ElementActions(get());
    }

    public BrowserActions browser(){
        return new BrowserActions(get());
    }






}
