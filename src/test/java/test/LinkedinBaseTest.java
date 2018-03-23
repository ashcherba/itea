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
    @Parameters({"browserType","envUrl"})
    @BeforeMethod
    public void beforeTest(@Optional("firefox")String browserType, @Optional("https://www.linkedin.com/") String envUrl){
        /*if (browserType.toLowerCase().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if (browserType.toLowerCase().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else {
            System.out.println("Unsupported browser");
        }
*/

        switch(browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.get(envUrl);
                break;
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.get(envUrl);
                break;
            default:
                System.out.println("Unsupported browser");
                break;
        }
        //driver.get("https://www.linkedin.com/");
        landingPage = new LinkedinLandingPage(driver);
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }
}
