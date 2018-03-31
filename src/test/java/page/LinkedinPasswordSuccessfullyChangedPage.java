package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordSuccessfullyChangedPage extends LinkedinBasePage{
    @FindBy(xpath = "//div[@class='fom-actions']/a[@href]")
    //    @FindBy(xpath = "//a[@class='btn-secondary-transparent']")
    private WebElement buttonToTheMainPage;

    public LinkedinPasswordSuccessfullyChangedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * verify that page is loaded and Button from this page is shown
     * @return true is the button that leads to the Home page appears
     */
    public boolean isLoaded() {
        boolean isLoaded = false;
        try {
            isLoaded = buttonToTheMainPage.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * Click on the button to the Home page
     * @return user to the Home page
     */
    public LinkedinHomePage goToTheMainPage() {
        buttonToTheMainPage.click();
        return new LinkedinHomePage(driver) {
            @Override
            public boolean isLoaded() {
                return false;
            }
        };
    }
}
