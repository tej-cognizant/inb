package com.cts.inb.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.cts.inb.utils.ExtentReportManager;

public class HomePageTest extends BaseTest {

    @Test(priority = 1, description = "Test navigation to Upcoming Bikes from Home Page")
    public void testNavigateToUpcomingBikes() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Navigate to Upcoming Bikes Test",
                "Verify navigation to Upcoming Bikes page from Home Page");

        test.info("Starting test: Navigate to Upcoming Bikes");

        test.info("Step 1: Hover on New Bikes menu");
        homePage.hoverOnNewBikes();
        test.pass("Successfully hovered on New Bikes menu");

        test.info("Step 2: Click on Upcoming Bikes link");
        homePage.clickUpcomingBikes();
        test.pass("Successfully clicked on Upcoming Bikes link");

        Thread.sleep(2000);

        test.pass("Test completed successfully");
    }

    @Test(priority = 2, description = "Test navigation to Used Cars from Home Page")
    public void testNavigateToUsedCars() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Navigate to Used Cars Test",
                "Verify navigation to Used Cars page from Home Page");

        test.info("Starting test: Navigate to Used Cars");

        test.info("Step 1: Hover on More menu");
        homePage.hoverOnMoreMenu();
        test.pass("Successfully hovered on More menu");

        test.info("Step 2: Click on Used Cars link");
        homePage.clickUsedCars();
        test.pass("Successfully clicked on Used Cars link");

        Thread.sleep(2000);

        test.pass("Test completed successfully");
    }
}
