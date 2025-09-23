package com.swaglabs.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.bidi.log.Log;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    private AllureUtils(){
        super();
    }
    static String ALLURE_RESULTS_PATH = "test-outputs/allure-results";
    static String WIN = "C:" + File.separator +
            "allure" + File.separator +
            "allure-2.24.0" + File.separator +
            "bin" + File.separator + "allure.bat";

    static String ALLURE_PATH = "C:" + File.separator +
            "allure" + File.separator +
            "allure-2.24.0" + File.separator +
            "bin" + File.separator + "allure";

    static String REPORT_PATH = "test-outputs/allure-report";

    public static void generateAllureReport() {
            TerminalUtils.executeCommand(WIN, "generate", ALLURE_RESULTS_PATH, "-o", REPORT_PATH,
                    "--clean", "--single-file");
            LogsUtils.info("Allure report generated on Windows");
    }

    public static String renameReport(){
        File oldName = new File(REPORT_PATH + File.separator + "index.html");
        File newName = new File(REPORT_PATH + File.separator + "report_" + TimeStampUtils.getTimeStamp() + ".html");
        FilesUtiles.renameFile(oldName , newName);
        return newName.getName();
    }


    public static void openReport(String reportName){
        String reportPath = REPORT_PATH + File.separator + reportName;
        if(PropertiesUtils.getPropertyValue("startAllureAutomatically").equalsIgnoreCase("true")){
            TerminalUtils.executeCommand("cmd.exe", "/c", "start", "\"\"", "\"" + reportPath + "\"");
            LogsUtils.info("Allure report opened on windows");
        }else {
            TerminalUtils.executeCommand("open" , reportPath);
            LogsUtils.info("Allure report opened on " + PropertiesUtils.getPropertyValue("os.name"));
        }

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
