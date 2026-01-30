package com.cts.inb.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.cts.inb.pages.HomePage;
import com.cts.inb.pages.LoginPage;
import com.cts.inb.pages.UpcomingBikes;
import com.cts.inb.pages.UsedCarsPage;
import com.cts.inb.pages.YamahaBikes;
import com.cts.inb.utils.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected UpcomingBikes upcomingBikesPage;
    protected YamahaBikes yamahaBikesPage;
    protected UsedCarsPage usedCarsPage;
    protected LoginPage loginPage;

    @BeforeSuite
    public void setupReport() {
        // Initialize Extent Report
        ExtentReportManager.initReport("reports/test-execution-report.html");
        System.out.println("Test Suite Started - Report initialized");
    }

    @BeforeMethod
    public void setup() {
        // Disable browser notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        // Launch browser
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get("https://www.zigwheels.com/");
        driver.manage().window().maximize();

        // Initialize Page Objects
        homePage = new HomePage(driver);
        upcomingBikesPage = new UpcomingBikes(driver);
        yamahaBikesPage = new YamahaBikes(driver);
        usedCarsPage = new UsedCarsPage(driver);
        loginPage = new LoginPage(driver);

        System.out.println("Browser launched and Page Objects initialized");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        // Flush Extent Report
        ExtentReportManager.flushReport();
        System.out.println("Test Suite Completed - Report generated");
    }
}
