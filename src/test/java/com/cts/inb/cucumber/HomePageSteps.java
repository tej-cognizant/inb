package com.cts.inb.cucumber;

import com.cts.inb.pages.HomePage;
import com.cts.inb.pages.UpcomingBikes;
import com.cts.inb.pages.UsedCarsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class HomePageSteps {

    private WebDriver driver;
    private HomePage homePage;
    private UpcomingBikes upcomingBikes;
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
    upcomingBikes = new UpcomingBikes(driver);
    usedCarsPage = new UsedCarsPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the ZigWheels homepage")
    public void i_open_the_zigwheels_homepage() {
        driver.get("https://www.zigwheels.com/");
    }

    @When("I navigate to Upcoming Bikes from the New Bikes menu")
    public void i_navigate_to_upcoming_bikes_from_the_new_bikes_menu() throws Exception {
        homePage.hoverOnNewBikes();
        homePage.clickUpcomingBikes();
    }

    @Then("I should land on the Upcoming Bikes page")
    public void i_should_land_on_the_upcoming_bikes_page() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("upcoming-bikes"), "Expected Upcoming Bikes page but URL was: " + url);
    }

    @When("I filter upcoming bikes under 2 lakhs")
    public void i_filter_upcoming_bikes_under_2_lakhs() throws Exception {
        upcomingBikes.clickBikesUnder2Lakhs();
    }

    @When("I open Yamaha upcoming bikes")
    public void i_open_yamaha_upcoming_bikes() throws Exception {
        upcomingBikes.clickYamahaBrand();
    }

    @Then("I should see Yamaha upcoming bikes")
    public void i_should_see_yamaha_upcoming_bikes() {
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.toLowerCase().contains("yamaha"), "Expected Yamaha bikes page but URL was: " + url);
    }

    @When("I navigate to Used Cars from the More menu")
    public void i_navigate_to_used_cars_from_the_more_menu() throws Exception {
        homePage.hoverOnMoreMenu();
        homePage.clickUsedCars();
    }

    @Then("I should see used cars in Chennai")
    public void i_should_see_used_cars_in_chennai() throws Exception {
        usedCarsPage.selectCity();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("used-car"), "Expected Used Cars page but URL was: " + url);
    }
}
