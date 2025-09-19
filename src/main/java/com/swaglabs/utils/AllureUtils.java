package com.swaglabs.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    private AllureUtils(){
        super();
    }

    public static void attachLogsToAllure(){
        try {
            File logsFile = FilesUtiles.getLatestFile(LogsUtils.LOG_PATH);
            if(!logsFile.exists()){
                LogsUtils.warn("File doesn't exist" , logsFile.getPath());
            }
            Allure.addAttachment("logs.log" , Files.readString(Path.of(logsFile.getPath())));
        } catch (Exception e) {
            LogsUtils.error("Failed to attach report to Allure" , e.getMessage());
        }
    }

    public static void attachScreenshotToAllure(String screenshotName , String screenshotPath){
        try {
            Allure.addAttachment(screenshotName , Files.newInputStream(Path.of(screenshotPath)));
            LogsUtils.info("Screen shot attached to Allure");
        }catch (Exception e){
            LogsUtils.error("Failed to attach screenshot to Allure" , e.getMessage());
        }
    }

}
