package com.cts.inb.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public final class ScreenShotUtils {

	private ScreenShotUtils() {
		// utility class
	}

	/**
	 * Capture a screenshot to the given file path. Creates parent directories if needed.
	 *
	 * @param driver   active WebDriver instance
	 * @param filePath absolute or relative destination path for the screenshot
	 */
	public static void takeScreenshot(WebDriver driver, String filePath) {
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(filePath);
			destination.getParentFile().mkdirs();
			FileUtils.copyFile(screenshot, destination);
			System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
		} catch (Exception e) {
			System.err.println("Failed to capture screenshot: " + e.getMessage());
		}
	}
}
