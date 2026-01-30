package com.cts.inb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class YamahaBikes extends BasePage {

    public YamahaBikes(WebDriver driver) {
        super(driver);
    }

    public void displayYamahaBikesUnder4Lakhs() {
        // Wait for at least one bike container to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modelItem")));
        
        // Find all bike containers
        List<WebElement> bikes = driver.findElements(By.className("modelItem"));

        System.out.println("Total Yamaha bikes found: " + bikes.size());
        System.out.println("=========================================");

        int validBikeCount = 0;

        for (int i = 0; i < bikes.size(); i++) {
            WebElement bike = bikes.get(i);

            try {
                // Extract bike model name
                List<WebElement> modelNameElements = bike.findElements(
                        By.xpath(".//div[contains(@class,'mke-ryt')]//strong"));

                if (modelNameElements.isEmpty()) {
                    continue;
                }

                String bikeName = modelNameElements.get(0).getText();

                // Extract price
                List<WebElement> priceElements = bike.findElements(
                        By.xpath(".//*[contains(text(),'Lakh')]"));

                String price = "";
                double priceValue = 0.0;
                if (!priceElements.isEmpty()) {
                    price = priceElements.get(0).getText().trim();

                    try {
                        String numericPart = price.replaceAll("Rs\\.?", "").replaceAll("Lakh.*", "").trim();
                        numericPart = numericPart.replaceAll("[^0-9.]", "");
                        if (!numericPart.isEmpty()) {
                            priceValue = Double.parseDouble(numericPart);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                // Filter: Only bikes with price < 4.00 Lakh
                if (priceValue >= 4.00 || priceValue == 0.0) {
                    continue;
                }

                // Extract expected launch date
                List<WebElement> launchDateElements = bike.findElements(
                        By.xpath(".//*[starts-with(text(),'Expected')]"));

                String expectedLaunch = "";
                if (!launchDateElements.isEmpty()) {
                    expectedLaunch = launchDateElements.get(0).getText();
                }

                // Display bike information
                if (!bikeName.isEmpty()) {
                    validBikeCount++;
                    System.out.println("Bike " + validBikeCount + ":");
                    System.out.println("Model Name: " + bikeName);
                    System.out.println("Price: " + price);
                    System.out.println("Expected Launch: " + (expectedLaunch.isEmpty() ? "Not Available" : expectedLaunch));
                    System.out.println("-----------------------------------------");
                }

            } catch (Exception e) {
                continue;
            }
        }

        System.out.println("Total bikes under 4.00 Lakh displayed: " + validBikeCount);
    }
}
