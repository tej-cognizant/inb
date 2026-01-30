package com.cts.inb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class UsedCarsPage extends BasePage {

    // Locators using @FindBy
    @FindBy(css = "li a[data-city_id='280']")
    private WebElement cityLink;

    public UsedCarsPage(WebDriver driver) {
        super(driver);
    }

    public void selectCity() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(cityLink));
        cityLink.click();
        Thread.sleep(2000);
    }

    public void displayUsedCars() {
        // Find car links dynamically to avoid stale element issues
        List<WebElement> cars = driver.findElements(By.cssSelector("a.fnt-22"));

        int limit = Math.min(10, cars.size());
        System.out.println("\n=========================================");
        System.out.println("Used Cars in Chennai List");
        System.out.println("=========================================");

        for (int i = 0; i < limit; i++) {
            String carName = cars.get(i).getText();
            if (!carName.isEmpty()) {
                System.out.println((i + 1) + ". " + carName);
            }
        }
        System.out.println("=========================================");
    }
}
