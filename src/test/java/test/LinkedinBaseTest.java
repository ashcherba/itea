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
     * Chain of calls to run before each test method:
     * 1.downloads the latest version of the WebDriver binary and exports the proper Java system variable
     * 2.creates the driver object, depending on browser type
     * 3.opens environment link
     * 4. creates new object of the GoogleStartPage class
     * @param browserType - the browser(Firefox, Chrome), used for test run
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
