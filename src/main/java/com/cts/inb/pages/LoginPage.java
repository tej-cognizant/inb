package com.cts.inb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Locators using @FindBy
    @FindBy(id = "des_lIcon")
    //Fill code here
    private WebElement loginIcon;

    @FindBy(className = "googleSignIn")
    private WebElement googleSignInButton;

    @FindBy(id = "identifierId")
    private WebElement emailInput;

    @FindBy(xpath = "//button//span[text()='Next']")
    private WebElement nextButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginIcon() throws InterruptedException {
        scrollToTop();
        wait.until(ExpectedConditions.elementToBeClickable(loginIcon));
        loginIcon.click();
        Thread.sleep(2000);
    }

    public void clickGoogleSignIn() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(googleSignInButton));
        googleSignInButton.click();
        Thread.sleep(3000);
    }

    public void switchToGoogleLoginWindow() {
        String parentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void enterEmail(String email) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys(email);
        Thread.sleep(1000);
    }

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }

}
