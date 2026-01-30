package com.cts.inb.cucumber;

import com.cts.inb.pages.HomePage;
import com.cts.inb.pages.UpcomingBikes;
import com.cts.inb.pages.YamahaBikes;
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

public class YamahaBikesSteps {

    private WebDriver driver;
    private HomePage homePage;
    private UpcomingBikes upcomingBikes;
    private YamahaBikes yamahaBikes;

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
        yamahaBikes = new YamahaBikes(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I launch ZigWheels for Yamaha bikes")
    public void i_launch_zigwheels_for_yamaha_bikes() {
        driver.get("https://www.zigwheels.com/");
    }

    @When("I go to Upcoming Bikes from the New Bikes menu")
    public void i_go_to_upcoming_bikes_from_the_new_bikes_menu() throws Exception {
        homePage.hoverOnNewBikes();
        homePage.clickUpcomingBikes();
    }

    @When("I filter upcoming bikes under 2 lakhs for Yamaha")
    public void i_filter_upcoming_bikes_under_2_lakhs_for_yamaha() throws Exception {
        upcomingBikes.clickBikesUnder2Lakhs();
    }

    @When("I choose Yamaha upcoming bikes")
    public void i_choose_yamaha_upcoming_bikes() throws Exception {
        upcomingBikes.clickYamahaBrand();
    }

    @Then("I should see Yamaha bikes under 4 lakhs")
    public void i_should_see_yamaha_bikes_under_4_lakhs() {
        yamahaBikes.displayYamahaBikesUnder4Lakhs();
        int count = countYamahaBikesUnderFourLakhs();
        Assert.assertTrue(count > 0, "Expected at least one Yamaha bike under 4 lakhs, but found none");
    }

    private int countYamahaBikesUnderFourLakhs() {
        List<WebElement> bikes = driver.findElements(By.className("modelItem"));
        int valid = 0;

        for (WebElement bike : bikes) {
            try {
                List<WebElement> priceElements = bike.findElements(By.xpath(".//*[contains(text(),'Lakh')]") );
                if (priceElements.isEmpty()) {
                    continue;
                }
                String priceText = priceElements.get(0).getText();
                String numericPart = priceText.replaceAll("Rs\\.?,?", "").replaceAll("Lakh.*", "").replaceAll("[^0-9.]", "").trim();
                if (numericPart.isEmpty()) {
                    continue;
                }
                double priceValue = Double.parseDouble(numericPart);
                if (priceValue > 0 && priceValue < 4.0) {
                    valid++;
                }
            } catch (Exception ignored) {
                // Skip malformed entries
            }
        }
        return valid;
    }
}
