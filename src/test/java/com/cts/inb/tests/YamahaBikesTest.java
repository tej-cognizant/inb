package com.cts.inb.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.cts.inb.utils.ExtentReportManager;

public class YamahaBikesTest extends BaseTest {

    @Test(priority = 1, description = "Test Yamaha bikes under 4 Lakhs display functionality")
    public void testYamahaBikesUnder4Lakhs() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Yamaha Bikes Test",
                "Verify Yamaha bikes under 4 Lakhs are displayed correctly");

        test.info("Starting test: Display Yamaha bikes under 4 Lakhs");

        // Navigate to Upcoming Bikes
        test.info("Step 1: Hover on New Bikes menu");
        homePage.hoverOnNewBikes();
        test.pass("Successfully hovered on New Bikes menu");

        test.info("Step 2: Click on Upcoming Bikes");
        homePage.clickUpcomingBikes();
        test.pass("Successfully navigated to Upcoming Bikes page");

        // Filter bikes under 2 Lakhs
        test.info("Step 3: Click on Bikes Under 2 Lakhs filter");
        upcomingBikesPage.clickBikesUnder2Lakhs();
        test.pass("Successfully applied Bikes Under 2 Lakhs filter");

        // Select Yamaha brand
        test.info("Step 4: Click on Yamaha brand");
        upcomingBikesPage.clickYamahaBrand();
        test.pass("Successfully selected Yamaha brand");

        // Display Yamaha bikes under 4 Lakhs
        test.info("Step 5: Display Yamaha bikes under 4 Lakhs");
        yamahaBikesPage.displayYamahaBikesUnder4Lakhs();
        test.pass("Successfully displayed Yamaha bikes under 4 Lakhs");

        Thread.sleep(2000);

        test.pass("Test completed successfully");
    }
}
