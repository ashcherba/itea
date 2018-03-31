package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class LinkedinBasePage {
    WebDriver driver;

    /**
     * The constructor in which the current page driver is announced
     * @param driver - announced of the Web Driver on the current page
     */
    public LinkedinBasePage (WebDriver driver){
        this.driver = driver;
    }

    /**
     * @return Title of the currently opened Web Page
     */
    public String getPageTitle() { return driver.getTitle(); }

    /**
     * @return URL of the currently opened Web Page
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Wait until WebElement is Clickable on  the Web Page
     * @param webElement - WebElement to Wait for
     * @return WebElement after wait
     */
    public WebElement waitUntilElementIsClickable (WebElement webElement){
        waitUntilElementIsClickable(webElement, 10);
        return webElement;
    }

    /**
     * Wait announced period of time until WebElement is Clickable on the Web Page
     * @param webElement - WebElement to Wait for
     * @param timeOutInSeconds - The time that the system will wait for the appearance of WebElement
     * @return WebElement after wait
     */
    public WebElement waitUntilElementIsClickable (WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    /**
     * Wait until WebElement appears on the Web Page
     * @param webElement - Web Element to Wait for
     */
   public void waitUntilElementIsVisible (WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Verify that specific page loaded
     * @return true if page loaded or false - if not
     */
    public abstract boolean isLoaded();

}