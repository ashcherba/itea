package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;
import page.LinkedinSearchResults;

import java.util.concurrent.TimeUnit;

public class LinkedinSearchTest {
    WebDriver driver;
    LinkedinLandingPage landingPage;
    LinkedinHomePage homePage;
    LinkedinSearchResults searchResults;

    @BeforeMethod
    public void beforeTest(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com/");
        landingPage = new LinkedinLandingPage(driver);
        landingPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");
        homePage = new LinkedinHomePage(driver);
        searchResults = new LinkedinSearchResults(driver);
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void basicSearchTest(){
        homePage.searchForElements("hr");
        Assert.assertEquals(searchResults.results.size(), 10, "Number of results is wrong");

        while(searchResults.getCardTitle()){
            Assert.assertTrue(searchResults.cardTitle.contains("hr"),
                    "Searchterm "+"'hr'"+ " not found in cart");
        }
    }
}
