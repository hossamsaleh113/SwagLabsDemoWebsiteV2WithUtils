package com.swaglabs.utils.actions;

import com.swaglabs.utils.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Navigating to URL: {url} ")
    public void navigateToURL( String url) {
        driver.get(url);
        LogsManager.info("Navigated to URL", url);

    }

    @Step("Getting current URL")
    public String getCurrentURL() {
        LogsManager.info("Current URL is:", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public String getPageTitle() {
        LogsManager.info("Current Page is:", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public void refreshPage() {
        LogsManager.info("Refreshing the page");
        driver.navigate().refresh();
    }

    @Step("Closing the browser")
    public void closeBrowser() {
        LogsManager.info("Closing the browser and ending session!");
        driver.quit();
    }

}
