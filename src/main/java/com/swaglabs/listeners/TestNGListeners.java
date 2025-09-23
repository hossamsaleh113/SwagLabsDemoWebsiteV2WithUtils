package com.swaglabs.listeners;

import com.swaglabs.utils.*;
import org.apache.commons.io.FileUtils;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    private final File allure_results = new File("test-outputs/allure-results");
    private final File logs = new File("test-outputs/Logs");
    private final File screenshots = new File("test-outputs/screenshots");


    @Override
    public void onExecutionStart() {
        LogsUtils.info("Test execution started");
        PropertiesUtils.loadProperties();
        FilesUtiles.deleteFiles(allure_results);
        FilesUtiles.cleanDirectory(logs);
        FilesUtiles.cleanDirectory(screenshots);

    }

    @Override
    public void onExecutionFinish() {
        AllureUtils.generateAllureReport();
        String reportName = AllureUtils.renameReport();
        AllureUtils.openReport(reportName);
        LogsUtils.info("Test execution finished");

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if (method.isTestMethod()) {

            CustomSoftAssertion.customAssertAll(testResult);
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot("skipped-" + testResult.getName());


            }

            AllureUtils.attachLogsToAllure();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test case", result.getName(), "passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("Test case", result.getName(), "failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test case", result.getName(), "skipped!");
    }
}
