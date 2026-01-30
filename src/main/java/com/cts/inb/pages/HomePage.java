package com.cts.inb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage extends BasePage {
    private Actions actions;

    // Locators using @FindBy
    @FindBy(xpath = "//span[normalize-space()='NEW BIKES']")
    private WebElement newBikesTab;

    @FindBy(xpath = "//a[@data-track-label='nav-upcoming-bikes' and contains(text(),'Upcoming Bikes')]")
    private WebElement upcomingBikesLink;

    @FindBy(xpath = "//span[normalize-space()='MORE']")
    private WebElement moreMenu;

    @FindBy(xpath = "//a[@data-track-label='nav-used-car' and contains(text(),'Used Cars')]")
    private WebElement usedCarsLink;

    public HomePage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public void hoverOnNewBikes() {
        wait.until(ExpectedConditions.visibilityOf(newBikesTab));
        actions.moveToElement(newBikesTab).perform();
    }

    public void clickUpcomingBikes() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(upcomingBikesLink));
        actions.moveToElement(upcomingBikesLink).pause(Duration.ofMillis(150)).click().perform();
        Thread.sleep(2000);
    }

    public void hoverOnMoreMenu() throws InterruptedException {
        // Scroll to top first to ensure menu is visible
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
        
        // Wait for element to be visible and clickable
        wait.until(ExpectedConditions.visibilityOf(moreMenu));
        wait.until(ExpectedConditions.elementToBeClickable(moreMenu));
        
        // Hover on MORE menu
        actions.moveToElement(moreMenu).perform();
        Thread.sleep(2000);
    }

    public void clickUsedCars() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(usedCarsLink));
        actions.moveToElement(usedCarsLink).pause(Duration.ofMillis(150)).click().perform();
        Thread.sleep(2000);
    }
}
