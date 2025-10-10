package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ElementActions {
    private WebDriver driver;
    private Waits waits ;
    private final By cartIconNumber = By.cssSelector("[data-test='shopping-cart-badge']");
    public ElementActions(WebDriver driver){
        this.driver =driver;
        waits = new Waits(driver);
    }

    @Step("Sending data {data} to element {locator}")
    public void type(By locator , String data){
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        findElement(locator).sendKeys(data);
        LogsUtils.info("Data entered:" , data , "in the field" , locator.toString() );
    }

    @Step("Clicking on element {locator}")
    public void click(By locator){
        waits.waitForElementClickable(locator);
        scrollToElement(locator);
        findElement(locator).click();
        LogsUtils.info("Clicking on element:" , locator.toString());
    }

    @Step("Getting text from the element: {locator}")
    public String getText(By locator){
        waits.waitForElementVisible(locator);
        scrollToElement(locator);
        LogsUtils.info("Getting text form element:" , locator.toString() , "Text" , findElement( locator).getText());
        return findElement(locator).getText();
    }

    @Step("Finding element: {locator}")
    public WebElement findElement( By locator){
        LogsUtils.info("Finding element" , locator.toString());
        waits.waitForElementPresent(locator);
        return driver.findElement(locator);
    }

    @Step("Finding elements: {locator}")
    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }


    @Step("Getting text from a text field")
    public  String getTextFromInputField( By locator){
        return findElement(locator).getDomAttribute("value");
    }

    @Step("Scrolling to element: {locator} ")
    public void scrollToElement( By locator){
        LogsUtils.info("Scrolling to element" , locator.toString());
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);"
                , findElement(locator));
    }

    @Step("Getting items count from cart icon")
    public String getCartIconNumber(){
        if(!findElement(cartIconNumber).isDisplayed()){
            return "0";
        }
        return getText(cartIconNumber);
    }

    @Step("Get number of Elements")
    public int getNumberOfElements(By locator){
        return findElements(locator).size();
    }




}
