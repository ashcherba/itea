import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    @Test
    public void successfulLoginTest(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        WebElement emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        WebElement passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("iteatest@i.ua");
        passField.sendKeys("1q2w3e_4r");
        signInButton.click();
    }

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
    }
}
