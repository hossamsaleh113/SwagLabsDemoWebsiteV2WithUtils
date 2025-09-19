package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private BrowserActions() {
    }


    @Step("Navigating to URL: {url} ")
    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
        LogsUtils.info("Navigated to URL", url);

    }

    @Step("Getting current URL")
    public static String getCurrentURL(WebDriver driver) {
        LogsUtils.info("Current URL is:", driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getPageTitle(WebDriver driver) {
        LogsUtils.info("Current Page is:", driver.getTitle());
        return driver.getTitle();
    }

    @Step("Refreshing the page")
    public static void refreshPage(WebDriver driver) {
        LogsUtils.info("Refreshing the page");
        driver.navigate().refresh();
    }

    @Step("Closing the browser")
    public static void closeBrowser(WebDriver driver) {
        LogsUtils.info("Closing the browser and ending session!");
        driver.quit();
    }
}
