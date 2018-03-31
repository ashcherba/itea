package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {
    @FindBy(id = "userName-requestPasswordReset")
    private WebElement userNameField;

    @FindBy(id = "btnSubmitResetRequest")
    private  WebElement submitButton;

    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @param userEmail - email address of the user to which system should send the link
     * @return the next page, Reset Submit Page
     */
    public LinkedinPasswordResetSubmitPage submitEmail(String userEmail) {
        userNameField.sendKeys("aashcherba.qa@gmail.com");
        submitButton.click();
        return new LinkedinPasswordResetSubmitPage (driver);
    }

    /**
     *  verifies if page is loaded, by checking the appearance of the button
     * @return true if User Name field is displayed and false - if not
     */
    public boolean isLoaded() {
        boolean isLoaded;
        try {
            isLoaded = userNameField.isDisplayed();
        }
        catch (NoSuchElementException e){
            isLoaded = false;
        }
        return isLoaded;
    }
}
