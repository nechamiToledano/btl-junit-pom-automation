package com.btl.tests;

import com.btl.extensions.DriverExtension;
import com.btl.extensions.ReportExtension;
import com.btl.managers.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

@ExtendWith({DriverExtension.class, ReportExtension.class})

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.btl.gov.il/");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
