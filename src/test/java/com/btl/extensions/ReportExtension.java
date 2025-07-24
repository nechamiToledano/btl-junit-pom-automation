package com.btl.extensions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.extension.*;

import com.btl.managers.DriverManager;
import com.btl.managers.ReportManager;
import com.btl.utils.ScreenshotUtil;

public class ReportExtension implements BeforeAllCallback, AfterTestExecutionCallback, AfterAllCallback {

    private static ExtentReports extent;

    @Override
    public void beforeAll(ExtensionContext context) {
        extent = ReportManager.getExtentReports();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        ExtentTest test = extent.createTest(context.getDisplayName());

        if (context.getExecutionException().isPresent()) {
            Throwable throwable = context.getExecutionException().get();

            String screenshotPath = ScreenshotUtil.takeScreenshot(
                    DriverManager.getDriver(), context.getDisplayName());

            test.log(Status.FAIL, throwable);
            try {
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.warning("Could not attach screenshot: " + e.getMessage());
            }
        } else {
            test.pass("Test passed");
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        ReportManager.flushReport();
    }
}
