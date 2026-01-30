package com.cts.inb.utils;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
import java.io.File;
 
/**
 * ExtentReportManager - Utility class to manage Extent Reports
 * This class provides methods to initialize, create, and finalize reports
 */
public class ExtentReportManager {
   
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
   
    /**
     * Initialize Extent Report with default settings
     * @param reportPath Path where the report should be generated
     */
    public static void initReport(String reportPath) {
        sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
       
        // Set system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Application", "Identify New Bikes");
       
        // Configure Spark Reporter
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Identify New Bikes - Test Report");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
    }
   
    /**
     * Initialize Extent Report with XML configuration
     * @param reportPath Path where the report should be generated
     * @param xmlConfigPath Path to XML configuration file
     */
    public static void initReportWithXMLConfig(String reportPath, String xmlConfigPath) {
        sparkReporter = new ExtentSparkReporter(reportPath);
       
        // Load XML configuration
        try {
            sparkReporter.loadXMLConfig(new File(xmlConfigPath));
        } catch (Exception e) {
            System.err.println("Error loading XML config: " + e.getMessage());
        }
       
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
       
        // Set system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }
   
    /**
     * Initialize Extent Report with JSON configuration
     * @param reportPath Path where the report should be generated
     * @param jsonConfigPath Path to JSON configuration file
     */
    public static void initReportWithJSONConfig(String reportPath, String jsonConfigPath) {
        sparkReporter = new ExtentSparkReporter(reportPath);
       
        // Load JSON configuration
        try {
            sparkReporter.loadJSONConfig(new File(jsonConfigPath));
        } catch (Exception e) {
            System.err.println("Error loading JSON config: " + e.getMessage());
        }
       
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
       
        // Set system information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
    }
   
    /**
     * Create a new test in the report
     * @param testName Name of the test
     * @return ExtentTest object
     */
    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }
   
    /**
     * Create a new test with description
     * @param testName Name of the test
     * @param description Description of the test
     * @return ExtentTest object
     */
    public static ExtentTest createTest(String testName, String description) {
        return extent.createTest(testName, description);
    }
   
    /**
     * Finalize the report - must be called at the end
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
   
    /**
     * Get ExtentReports instance
     * @return ExtentReports object
     */
    public static ExtentReports getExtentReports() {
        return extent;
    }
}