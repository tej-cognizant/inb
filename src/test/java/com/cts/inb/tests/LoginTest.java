package com.cts.inb.tests;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.cts.inb.utils.ExtentReportManager;
import com.cts.inb.utils.ScreenShotUtils;

public class LoginTest extends BaseTest {

    @Test(priority = 3, description = "Test Google login functionality and capture error screenshot")
    public void testGoogleLogin() throws InterruptedException {
        ExtentTest test = ExtentReportManager.createTest("Google Login Test",
                "Verify Google login flow and capture error screenshot");

        test.info("Starting test: Google Login with invalid credentials");

        // Navigate to login
        test.info("Step 1: Click on Login icon");
        loginPage.clickLoginIcon();
        test.pass("Successfully clicked on Login icon");

        test.info("Step 2: Click on Google Sign In");
        loginPage.clickGoogleSignIn();
        test.pass("Successfully clicked on Google Sign In");

        // Switch to Google login window
        test.info("Step 3: Switch to Google login popup window");
        loginPage.switchToGoogleLoginWindow();
        test.pass("Successfully switched to Google login window");

        // Enter email
        test.info("Step 4: Enter email address");
        loginPage.enterEmail("abc@gmail.com");
        test.pass("Successfully entered email: abc@gmail.com");

        // Click Next
        test.info("Step 5: Click Next button");
        loginPage.clickNext();
        test.pass("Successfully clicked Next button");

        // Take screenshot
        test.info("Step 6: Capture error screenshot");
    String screenshotPath = "C:/Users/2457511/Desktop/FinalProject/identify_new_bikes_1/src/main/resources/screenshots/google_login_error.png";
    ScreenShotUtils.takeScreenshot(driver, screenshotPath);
        test.pass("Screenshot captured successfully");

        Thread.sleep(3000);

        test.pass("Test completed successfully");
    }
}
