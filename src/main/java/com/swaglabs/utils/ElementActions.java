package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
    private ElementActions(){}

    @Step("Sending data {data} to element {locator}")
    public static void sendData(WebDriver driver , By locator , String data){
        Waits.waitForElementVisible(driver , locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver , locator).sendKeys(data);
        LogsUtils.info("Data entered:" , data , "in the field" , locator.toString() );
    }

    @Step("Clicking on element {locator}")
    public static void clickElement(WebDriver driver , By locator){
        Waits.waitForElementClickable(driver , locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver , locator).click();
        LogsUtils.info("Clicking on element:" , locator.toString());
    }

    @Step("Getting text from the element: {locator}")
    public static String getText(WebDriver driver , By locator){
        Waits.waitForElementVisible(driver , locator);
        Scrolling.scrollToElement(driver, locator);
        LogsUtils.info("Getting text form element:" , locator.toString() , "Text" , findElement(driver, locator).getText());
        return findElement(driver , locator).getText();
    }

    @Step("Finding element: {locator}")
    public static WebElement findElement(WebDriver driver , By locator){
        LogsUtils.info("Finding element" , locator.toString());
        return driver.findElement(locator);
    }

    @Step("Getting text from a text field")
    public static String getTextFromInputField(WebDriver driver , By locator){
        return findElement(driver , locator).getDomAttribute("value");
    }



}
