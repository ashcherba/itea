import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinLoginPage {
    WebDriver driver;
    public LinkedinLoginPage (WebDriver driver) {
        this.driver = driver;
    }
    private WebElement emailField;
    private WebElement passField;
    private WebElement signInButton;

    public void initElements() {
        emailField = driver.findElement(By.xpath("//input[(@id='login-email')]"));
        waitUntilElementsClickable(emailField,5);
        passField = driver.findElement(By.xpath("//input[(@id='login-password')]"));
        signInButton = driver.findElement(By.id("login-submit"));
    }

    public void loginAs(String email, String password){
        initElements();
        emailField.sendKeys(email);
        passField.sendKeys(password);
        signInButton.click();
    }

    public void waitUntilElementsClickable(WebElement WebElement){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(WebElement));
    }

    public void waitUntilElementsClickable(WebElement WebElement, int timeOutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.until(ExpectedConditions.elementToBeClickable(WebElement));
    }
}
