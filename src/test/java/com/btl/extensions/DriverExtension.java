package com.btl.extensions;

import com.btl.managers.DriverManager;
import org.junit.jupiter.api.extension.*;

public class DriverExtension implements BeforeAllCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        DriverManager.getDriver();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        DriverManager.quitDriver();
    }
}
