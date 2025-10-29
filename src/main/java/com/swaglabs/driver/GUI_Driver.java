package com.swaglabs.driver;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.actions.BrowserActions;
import com.swaglabs.utils.actions.ElementActions;
import com.swaglabs.validations.SoftAssertion;
import com.swaglabs.validations.Validation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;


public class GUI_Driver {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private final WebDriver driver;


    public GUI_Driver(String browserName){
        Browser browserType = Browser.valueOf(browserName.toUpperCase());
        LogsManager.info("Starting browser on thread " + Thread.currentThread().getId(),
                browserType.toString());
        AbstractDriver abstractDriver = browserType.getDriverFactory();
        WebDriver newDriver = ThreadGuard.protect(abstractDriver.startDriver());
        setDriver(newDriver);
        this.driver = newDriver;
        LogsManager.info("Browser started successfully",
                "Thread: " + Thread.currentThread().getId() +
                        ", Browser: " + browserType);
    }




    private void setDriver(WebDriver driver){
        driverThreadLocal.set(driver);
    }

    public static void quit() {
        if (get() != null) {
            get().quit();
            driverThreadLocal.remove();
        }
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

    public Validation validations(){return new Validation(get());}

    public SoftAssertion verifications(){return new SoftAssertion(get());}







}
