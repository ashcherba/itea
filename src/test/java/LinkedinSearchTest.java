import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchTest {
        WebDriver driver;

        @BeforeTest
        public void beforeTest() throws InterruptedException {
            driver = new FirefoxDriver();
            driver.get("https://www.linkedin.com");
            LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
            loginPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");

            //search
            sleep(5000);
            WebElement inputField = driver.findElement(By.xpath("//nav[@id='extended-nav']//input"));
            inputField.sendKeys("hr");
            WebElement searchButton = driver.findElement(By.xpath("//button[@class='search-typeahead-v2__button typeahead-icon']"));
            searchButton.click();
            sleep(5000);
        }

        @AfterTest
        public void afterTest() {
            driver.close();
        }

        @Test
        public void basicSearchTest() {
            //verify that Total number of Search Results equals 10

            List<WebElement> searchResults = driver.findElements(By.xpath("//ul[@class='search-results__list list-style-none']/li[not(ul)]"));
            Assert.assertEquals(searchResults.size(), 10, "Search result doesn't contain 10 links");
        }

            @Test
            public void basicSearchTest2() {
                //verify that each search result link contains entered word

                List<WebElement> titlesOfResults = driver.findElements(By.xpath("//span[@class='name actor-name']"));
                for (WebElement element : titlesOfResults) {
                    String title = element.getText().toLowerCase();
                    Assert.assertTrue(title.contains("hr"), "Some results don't contain search term");
                }
            /*locators:
            //ul/li/div/div/div/p[1]
            //li[@class='search-result search-result__occluded-item ember-view']/div
            //ul[@class='search-results__list list-style-none']/li'
            input[@placeholder='Search']
            *[@type='search-icon]
            div[contains(@class,'search-result--person')]
            //li/div//span[@class='name actor-name']
            //ul[@class='search-results__list list-style-none']/li[count(ul)=0]
             */
        }
    }
