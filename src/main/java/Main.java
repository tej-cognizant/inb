import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.cts.inb.pages.HomePage;
import com.cts.inb.pages.LoginPage;
import com.cts.inb.pages.UpcomingBikes;
import com.cts.inb.pages.UsedCarsPage;
import com.cts.inb.pages.YamahaBikes;
import com.cts.inb.utils.ScreenShotUtils;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Disable browser notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        // Launch browser
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get("https://www.zigwheels.com/");
        driver.manage().window().maximize();

        // Initialize Page Objects
        HomePage homePage = new HomePage(driver);
        UpcomingBikes upcomingBikesPage = new UpcomingBikes(driver);
        YamahaBikes yamahaBikesPage = new YamahaBikes(driver);
        UsedCarsPage usedCarsPage = new UsedCarsPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // Navigate to Upcoming Bikes
        homePage.hoverOnNewBikes();
        homePage.clickUpcomingBikes();

        // Filter bikes under 2 Lakhs and select Yamaha
        upcomingBikesPage.clickBikesUnder2Lakhs();
        upcomingBikesPage.clickYamahaBrand();

        // Display Yamaha bikes under 4 Lakhs
        yamahaBikesPage.displayYamahaBikesUnder4Lakhs();

        // Navigate to Used Cars
        homePage.hoverOnMoreMenu();
        homePage.clickUsedCars();

        // Select city and display used cars
        usedCarsPage.selectCity();
        usedCarsPage.displayUsedCars();

        Thread.sleep(2000);

        // Login with Google
        loginPage.clickLoginIcon();
        loginPage.clickGoogleSignIn();
        loginPage.switchToGoogleLoginWindow();
        loginPage.enterEmail("abc@gmail.com");
        loginPage.clickNext();

        // Take screenshot
    ScreenShotUtils.takeScreenshot(driver,
        "C:/Users/2457511/Desktop/FinalProject/identify_new_bikes_1/src/main/resources/screenshots/google_login_error.png");

        Thread.sleep(3000);

        driver.quit();
    }
}