package com.swaglabs.validations;

import com.swaglabs.utils.Waits;
import com.swaglabs.utils.actions.ElementActions;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected Waits wait;
    protected ElementActions actions;

    protected BaseAssertion(WebDriver driver){
        this.driver = driver;
        wait = new Waits(driver);
        actions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition);
    protected abstract void assertFalse(boolean condition );
    protected abstract void assertEquals(String actual , String expected , String massage);
    protected abstract void assertNotEquals(String expected, String actual, String massage);
    protected abstract void assertPageURL(String expected);
    protected abstract void assertPageTitle(String expected);




}
