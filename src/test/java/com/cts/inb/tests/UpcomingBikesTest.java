package com.cts.inb.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.cts.inb.utils.ExtentReportManager;

public class UpcomingBikesTest extends BaseTest {

    @Test(priority = 1, description = "Test to display Yamaha bikes under 4 Lakhs from Upcoming Bikes section")
    public void testYamahaBikesUnder4Lakhs() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Yamaha Bikes Under 4 Lakhs Test",
                "Verify upcoming Yamaha bikes under 4 Lakhs are displayed");

        test.info("Starting test: Display Yamaha bikes under 4 Lakhs");

        // Navigate to Upcoming Bikes
        test.info("Step 1: Hover on NEW BIKES menu");
        homePage.hoverOnNewBikes();

        test.info("Step 2: Click on Upcoming Bikes");
        homePage.clickUpcomingBikes();
        test.pass("Successfully navigated to Upcoming Bikes page");

        // Filter bikes under 2 Lakhs
        test.info("Step 3: Click on Bikes Under 2 Lakhs");
        upcomingBikesPage.clickBikesUnder2Lakhs();
        test.pass("Successfully filtered bikes under 2 Lakhs");

        // Select Yamaha brand
        test.info("Step 4: Select Yamaha brand");
        upcomingBikesPage.clickYamahaBrand();
        test.pass("Successfully selected Yamaha brand");

        // Display Yamaha bikes under 4 Lakhs
        test.info("Step 5: Display Yamaha bikes under 4 Lakhs");
        yamahaBikesPage.displayYamahaBikesUnder4Lakhs();
        test.pass("Successfully displayed Yamaha bikes under 4 Lakhs");

        test.pass("Test completed successfully");
    }
}
