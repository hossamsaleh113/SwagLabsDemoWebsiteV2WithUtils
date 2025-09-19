package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private Waits(){};

    public static WebElement waitForElementPresent(WebDriver driver , By locator){
        LogsUtils.info("Waiting for element to be present:" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(10))
                .until(driver1 -> driver1.findElement(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver , By locator){
        LogsUtils.info("Waiting for element to be visible:" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementPresent(driver , locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public static WebElement waitForElementClickable(WebDriver driver , By locator){
        LogsUtils.info("Waiting for element to be clickable:" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementVisible(driver , locator);
                    return element.isEnabled() ? element : null;
                });
    }
}
