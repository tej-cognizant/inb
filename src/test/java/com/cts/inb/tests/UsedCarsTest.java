package com.cts.inb.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.cts.inb.utils.ExtentReportManager;

public class UsedCarsTest extends BaseTest {

    @Test(priority = 2, description = "Test to display used cars in Chennai")
    public void testUsedCarsInChennai() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Used Cars in Chennai Test",
                "Verify used cars list in Chennai is displayed");

        test.info("Starting test: Display Used Cars in Chennai");

        // Navigate to Used Cars
        test.info("Step 1: Hover on MORE menu");
        homePage.hoverOnMoreMenu();

        test.info("Step 2: Click on Used Cars");
        homePage.clickUsedCars();
        test.pass("Successfully navigated to Used Cars page");

        // Select Chennai city
        test.info("Step 3: Select Chennai city");
        usedCarsPage.selectCity();
        test.pass("Successfully selected Chennai city");

        // Display used cars
        test.info("Step 4: Display top 10 used cars");
        usedCarsPage.displayUsedCars();
        test.pass("Successfully displayed used cars in Chennai");

        test.pass("Test completed successfully");
    }
}
