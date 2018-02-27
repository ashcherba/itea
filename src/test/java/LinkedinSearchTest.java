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
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LinkedinSearchTest {
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.linkedin.com");
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }

    @Test
    public void basicSearchTest() throws InterruptedException {
        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
        loginPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");
        //search
        sleep(10);
        String searchTerm = "qa";
        driver.findElement(By.xpath("//div[@class='nav-search-typeahead']//input")).sendKeys(searchTerm);
        driver.findElement(By.xpath("//*[@type='search-icon']")).click();
        sleep(10);
        //[contains(@class,'search-result__occluded-item')]
        List<WebElement> results = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));
        //int currentResultsNumber = results.size();
        //Assert.assertEquals(results.size(), 10, "Number of results is wrong");
        sleep(10);
        /*
        for (int i = 1; i < results.size(); i++) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", results.get(i));
            sleep(50);
            String cardTitle = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][+i+]//span[contains(@class, 'actor-name')]")).getText();
            System.out.println(cardTitle);
            //Assert.assertTrue(cardTitle.contains(searchTerm.toLowerCase()),"Search term "+searchTerm+" not found in cart number "+ Integer.toString(i));
        }
        */
        for(int j=0; j<10; j+=10) {
            for (int i = 1; i <= results.size(); i++) {
                JavascriptExecutor je = (JavascriptExecutor) driver;
                WebElement element = driver.findElement(By.xpath("//li[contains(@class,'search-result__occluded-item')][" + i + "]//p[1]"));
                je.executeScript("arguments[0].scrollIntoView(true);", element);
                System.out.println(element.getText());
                String cardTitle = element.getText().toLowerCase();
                Assert.assertTrue(cardTitle.contains(searchTerm),"Search term "+searchTerm+" not found in cart number " + i);
            }
        }
    }
}
//li[contains(@class,'search-result__occluded-item')][" + i + "]//p[1]
//li[contains(@class,'search-result__occluded-item')][" + i + "]//span[contains(@class, 'actor-name')]
