package com.swaglabs.utils.actions;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.Waits;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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
        scrollToElementJS(locator);
        findElement(locator).sendKeys(data);
        LogsManager.info("Data entered:" , data , "in the field" , locator.toString() );
    }

    @Step("Clicking on element {locator}")
    public void click(By locator){
        waits.waitForElementClickable(locator);
        scrollToElementJS(locator);
        findElement(locator).click();
        LogsManager.info("Clicking on element:" , locator.toString());
    }

    @Step("Getting text from the element: {locator}")
    public String getText(By locator){
        waits.waitForElementVisible(locator);
        scrollToElementJS(locator);
        LogsManager.info("Getting text form element:" , locator.toString() , "Text" , findElement( locator).getText());
        return findElement(locator).getText();
    }

    @Step("Finding element: {locator}")
    public WebElement findElement( By locator){
        LogsManager.info("Finding element" , locator.toString());
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
    public void scrollToElementJS(By locator){
        LogsManager.info("Scrolling to element:" , locator.toString());
        String jsScript = "arguments[0].scrollIntoView({ behavior: \"auto\", block: \"center\", inline: \"center\" });";

        ((JavascriptExecutor) driver).executeScript(
                jsScript,
                findElement(locator)
        );
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
