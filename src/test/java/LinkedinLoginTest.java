import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    }

    @AfterClass
    public void afterClass () {
    }
    @BeforeMethod
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com");
    }
    @AfterMethod
    public void afterTest() {
        driver.close();

    }


    @Test
    public void successfulLoginTest() throws InterruptedException {
        driver.get("https://www.linkedin.com");
        String initialGetTitle = driver.getTitle();
        String initialPageUrl = driver.getCurrentUrl();

        WebElement emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        WebElement passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("aashcherba@bigmir.net\n");
        passField.sendKeys("qwertyQ1");
        signInButton.click();

        WebElement userIcon = driver.findElement(By.id("profile-nav-item"));
        Assert.assertTrue(userIcon.isDisplayed(), "User icon was not displayed");
        Assert.assertNotEquals(driver.getTitle(),initialGetTitle, "Page title didn't change");
        Assert.assertNotEquals(driver.getCurrentUrl(),initialPageUrl, "Page URL didn't change");
        Assert.assertEquals(driver.getTitle(), "Крупнейшая в мире сеть профессиональных контактов | LinkedIn", "Login page Title is wrong");


//my HW:
        /*sleep(5000);
        String currentLink = driver.getCurrentUrl();
        Assert.assertEquals(currentLink, "https://www.linkedin.com/feed/");

        WebElement homePage = driver.findElement(By.xpath("//a[(@class='nav-item__link nav-item__link--underline js-nav-item-link active')]"));
        Assert.assertTrue(homePage.isEnabled(), "Invalid credentials");
        driver.quit();
        */
    }

/*    @Test
    public void successfulLoginTest2() throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        WebElement emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        WebElement passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("aashcherba@bigmir.net\n");
        passField.sendKeys("qwertyQ1");
        signInButton.click();

        sleep(5000);
        String currentLink = driver.getCurrentUrl();
        Assert.assertEquals(currentLink, "https://www.linkedin.com/feed/");
        driver.quit();
    }
*/
    @Test
    public void negativeLoginTest(){
        driver.get("https://www.linkedin.com/");
        WebElement emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        WebElement passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("test@ukr.net");
        passField.sendKeys("12345");
        signInButton.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()=\"\")]"));
        Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed");
    }
}
