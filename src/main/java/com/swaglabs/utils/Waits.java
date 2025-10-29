package com.swaglabs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Waits {
    private WebDriver driver;
    public Waits(WebDriver driver){
        this.driver = driver;
    };

    public WebElement waitForElementPresent(By locator){
        LogsManager.info("Waiting for element to be present:" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(10))
                .until(driver1 -> driver1.findElement(locator));
    }

    public WebElement waitForElementVisible(By locator){
        LogsManager.info("Waiting for element to be visible:" , locator.toString());
        return new WebDriverWait(driver , Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementPresent(locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public void waitForElementClickable(By locator){
        LogsManager.info("Waiting for element to be clickable:" , locator.toString());
         new WebDriverWait(driver , Duration.ofSeconds(10)).
                until(driver1 -> {
                    WebElement element = waitForElementVisible(locator);
                    return element.isEnabled() ? element : null;
                });
    }


    public FluentWait<WebDriver> waitFor(){
               return new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(Long.parseLong(PropertiesUtils.getProperty("DEFAULT_WAIT"))))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoreAll(ignoreExceptions());

    }

    private ArrayList<Class<? extends Exception>> ignoreExceptions(){
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add( NoSuchElementException.class);
        exceptions.add(IOException.class);
        exceptions.add(FileNotFoundException.class);
        return exceptions;
    }
}
