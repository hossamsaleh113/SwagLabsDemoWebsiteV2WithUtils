package com.swaglabs.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends AbstractDriver implements DriverOptionsAbstract<FirefoxOptions>{
    @Override
    public WebDriver startDriver() {
        return new FirefoxDriver(getOptions());
    }

    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        return firefoxOptions;
    }
}
