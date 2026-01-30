package com.cts.inb.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * ExtentReportDemo - Simple demonstration of Extent Reports
 */
public class ExtentReportDemo {

    public static void main(String[] args) {
        System.out.println("Creating Extent Report...");

        // Create reports directory
        new File("reports").mkdirs();

        // Create Spark Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/test-report.html");
        
        // Configure reporter
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setReportName("Identify New Bikes - Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        // Create ExtentReports instance
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));

        // Create sample test
        ExtentTest test = extent.createTest("Sample Test");
        test.pass("Step 1: Test started");
        test.pass("Step 2: Test passed successfully");
        test.info("Report generated successfully");

        // Flush report
        extent.flush();
        
        System.out.println("Report generated: reports/test-report.html");
    }
}