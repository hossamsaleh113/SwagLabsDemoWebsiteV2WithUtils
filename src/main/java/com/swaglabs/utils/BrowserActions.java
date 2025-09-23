package com.swaglabs.utils;

import com.swaglabs.driver.GUI_Driver;
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
        LogsUtils.info("Navigated to URL", url);

    }

    @Step("Getting current URL")
    public String getCurrentURL() {
        LogsUtils.info("Current URL is:", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public String getPageTitle() {
        LogsUtils.info("Current Page is:", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public void refreshPage() {
        LogsUtils.info("Refreshing the page");
        driver.navigate().refresh();
    }

    @Step("Closing the browser")
    public void closeBrowser() {
        LogsUtils.info("Closing the browser and ending session!");
        driver.quit();
    }
}
