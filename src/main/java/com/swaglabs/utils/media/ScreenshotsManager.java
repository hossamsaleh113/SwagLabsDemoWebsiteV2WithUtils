package com.swaglabs.utils.media;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.reports.AllureAttachmentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

public class ScreenshotsManager {
    public static final String SCREENSHOT_PATH = "test-outputs/screenshots/";
    private ScreenshotsManager(){
        super();
    }

    public static void takeScreenshot(String screenshotName){
        try {
            File screenshot =  ((TakesScreenshot) GUI_Driver.get()).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + "_" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshot , screenshotFile);
            AllureAttachmentManager.attachScreenshot(screenshotName , screenshotFile.getPath() );
        } catch (Exception e) {
            LogsManager.error("Failed to take screenshot" + e.getMessage());
        }
    }

    public static void takeScreenShotOfElement(By locator){
        try {
            String elementName = GUI_Driver.get().findElement(locator).getAccessibleName();
            File screenshot = GUI_Driver.get().findElement(locator).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + elementName + "_" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshot , screenshotFile);
            AllureAttachmentManager.attachScreenshot(elementName , screenshotFile.getPath() );
        } catch (Exception e) {
            LogsManager.error("Failed to take screenshot" + e.getMessage());
        }

    }
}
