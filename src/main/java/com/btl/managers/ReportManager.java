package com.btl.managers;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extent;

public static ExtentReports getExtentReports() {
    if (extent == null) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        try {
            sparkReporter.loadXMLConfig("html-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } 

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
    return extent;
}


    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
