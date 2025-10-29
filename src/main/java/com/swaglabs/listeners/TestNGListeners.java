package com.swaglabs.listeners;

import com.swaglabs.utils.*;
import com.swaglabs.utils.media.ScreenshotsManager;
import com.swaglabs.utils.reports.AllureAttachmentManager;
import com.swaglabs.utils.reports.AllureConstants;
import com.swaglabs.utils.reports.AllureEnvironmentManager;
import com.swaglabs.utils.reports.AllureReportGenerator;
import com.swaglabs.validations.SoftAssertion;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {

    private final File allure_results = new File("test-outputs/allure-results");
    private final File logs = new File("test-outputs/Logs");
    private final File screenshots = new File("test-outputs/screenshots");


    @Override
    public void onExecutionStart() {
        PropertiesUtils.loadProperties();
        AllureEnvironmentManager.setEnvironmentVariables();
        LogsManager.info("Test execution started");
        FilesUtils.deleteFiles(allure_results);
        FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(screenshots);

    }

    @Override
    public void onExecutionFinish() {
        AllureReportGenerator.copyHistory();
        AllureReportGenerator.generateReports(false);
        AllureReportGenerator.generateReports(true);
        AllureReportGenerator.openReport();
        LogsManager.info("Test execution finished");

    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        if (method.isTestMethod()) {

            SoftAssertion.assertAll(testResult);
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotsManager.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsManager.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsManager.takeScreenshot("skipped-" + testResult.getName());
            }
            AllureAttachmentManager.attachLogs();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test case", result.getName(), "passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsManager.info("Test case", result.getName(), "failed!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test case", result.getName(), "skipped!");
    }
}
