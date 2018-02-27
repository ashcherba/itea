import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LinkedinBasePage {
    WebDriver driver;
    public LinkedinBasePage (WebDriver driver) {
        this.driver = driver;
    }
    WebElement userIcon;
    private void initElements(){
        userIcon = driver.findElement(By.id("profile-nav-item"));
    }

    public boolean isSignedIn() {
        initElements();
        waitUntilElementsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilElementsClickable(WebElement WebElement){
        waitUntilElementsClickable(WebElement, 10);
    }

    public void waitUntilElementsClickable(WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
