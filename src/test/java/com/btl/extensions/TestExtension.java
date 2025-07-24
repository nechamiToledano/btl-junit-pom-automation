package com.btl.extensions;

import com.btl.managers.DriverManager;
import com.btl.utils.ScreenshotUtil;
import org.junit.jupiter.api.extension.*;

public class TestExtension implements AfterTestExecutionCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        DriverManager.getDriver();
    }

    @Override
    public void afterEach(ExtensionContext context) {
        DriverManager.quitDriver();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            String testName = context.getDisplayName();
            String path = ScreenshotUtil.takeScreenshot(DriverManager.getDriver(), testName);
            System.out.println("Test failed. Screenshot saved to: " + path);
        }
    }
}
