package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LinkedinLandingPage;

public class LinkedinBaseTest {
    WebDriver driver;
    LinkedinLandingPage landingPage;
    String initialPageTitle;
    String initialPageUrl;

    /**
     * Switch for using different browsers (Chrome, Firefox)
     * @param browserType browser to be used for tests runs
     * @param envUrl link to the test start page
     */
    @Parameters({"browserType","envUrl"})
    @BeforeMethod
    public void beforeTest(@Optional("firefox")String browserType,
                           @Optional("https://www.linkedin.com/") String envUrl){

        switch(browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
              driver = new FirefoxDriver();
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Unsupported browser");
                break;
        }

        driver.get(envUrl);
        landingPage = new LinkedinLandingPage(driver) {
            @Override
            public boolean isLoaded() {
                return false;
            }
        };
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }
}
