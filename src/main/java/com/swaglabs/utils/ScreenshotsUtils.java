package com.swaglabs.utils;

import com.swaglabs.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

public class ScreenshotsUtils {
    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenshotsUtils(){
        super();
    }

    public static void takeScreenshot(String screenshotName){
        try {
            File screenshot =  ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + "_" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshot , screenshotFile);
            AllureUtils.attachScreenshotToAllure(screenshotName , screenshotFile.getPath() );
        } catch (Exception e) {
            LogsUtils.error("Failed to take screenshot" + e.getMessage());
        }
    }
}
