package com.cts.inb.cucumber;

import com.cts.inb.pages.HomePage;
import com.cts.inb.pages.UsedCarsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsedCarsSteps {

    private WebDriver driver;
    private HomePage homePage;
    private UsedCarsPage usedCarsPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        usedCarsPage = new UsedCarsPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the ZigWheels homepage for used cars")
    public void i_open_the_zigwheels_homepage_for_used_cars() {
        driver.get("https://www.zigwheels.com/");
    }

    @When("I go to Used Cars from the More menu")
    public void i_go_to_used_cars_from_the_more_menu() throws Exception {
        homePage.hoverOnMoreMenu();
        homePage.clickUsedCars();
    }

    @When("I select Chennai used cars")
    public void i_select_chennai_used_cars() throws Exception {
        usedCarsPage.selectCity();
    }

    @Then("I should see some used cars listed")
    public void i_should_see_some_used_cars_listed() {
        // Leverage existing method for display, then assert non-empty list
        usedCarsPage.displayUsedCars();
        List<WebElement> cars = driver.findElements(By.cssSelector("a.fnt-22"));
        Assert.assertTrue(!cars.isEmpty(), "Expected used cars list to be non-empty");
    }
}
