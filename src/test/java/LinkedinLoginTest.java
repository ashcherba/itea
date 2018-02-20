import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
    @Test
    public void successfulLoginTest() throws InterruptedException {
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

        WebElement homePage = driver.findElement(By.xpath("//a[(@class='nav-item__link nav-item__link--underline js-nav-item-link active')]"));
        Assert.assertTrue(homePage.isEnabled(), "Invalid credentials");
        driver.quit();
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
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        WebElement emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        WebElement passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("test@ukr.net");
        passField.sendKeys("12345");
        signInButton.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()=\"\")]"));
        Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed");
        driver.quit();
    }
}
