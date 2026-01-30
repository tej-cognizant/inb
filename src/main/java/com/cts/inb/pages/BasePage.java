package com.cts.inb.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void scrollByPercentage(double percentage) throws InterruptedException {
        long scrollHeight = (Long) js.executeScript("return document.documentElement.scrollHeight;");
        long scrollAmount = (long) (scrollHeight * percentage);
        js.executeScript("window.scrollTo(0, " + scrollAmount + ");");
        Thread.sleep(1000);
    }

    public void scrollToTop() throws InterruptedException {
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);
    }
}
