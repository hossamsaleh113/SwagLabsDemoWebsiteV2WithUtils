package com.swaglabs.utils.reports;

import com.swaglabs.utils.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.swaglabs.utils.PropertiesUtils.getProperty;
import static com.swaglabs.utils.reports.AllureConstants.*;

public class AllureReportGenerator {
    //Generate Allure report
    //--single-file - generate single file report
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        // allure generate -o reports --single-file --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeCommand(command.toArray(new String[0]));
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport() {
        String newFileName = AllureConstants.REPORT_PREFIX + TimeStampUtils.getTimeStamp() + AllureConstants.REPORT_EXTENSION;
        String oldName = AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString();
        FilesUtils.renameFile( oldName , newFileName);
        return AllureConstants.REPORT_PATH.resolve(newFileName).toString();
    }

//    //open Allure report in browser
//    public static void openReport(String reportFileName) {
//        if (!getProperty("executionType").toLowerCase().contains("local")) return;
//
//        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
//        switch (OSUtils.getCurrentOS()) {
//            case WINDOWS -> TerminalUtils.executeCommand("cmd.exe", "/c", "start", reportPath.toString());
//            case MAC , LINUX -> TerminalUtils.executeCommand("open", reportPath.toString());
//            default -> LogsManager.warn("Opening Allure Report is not supported on this OS.");
//        }
//    }


    public static void openReport(){
        if(getProperty("executionType").equalsIgnoreCase("local") || getProperty("executionType").equalsIgnoreCase("localHeadless")){
            String report = renameReport();
            FilesUtils.openFile(report);
        }
    }

    //copy history folder to results folder
    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.error("Error copying history files", e.getMessage());
        }
    }
}
