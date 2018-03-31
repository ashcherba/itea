package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinCreateNewPasswordPage extends LinkedinBasePage{
    @FindBy(id = "new_password-newPassword-passwordReset")
    private WebElement newPasswordField;

    @FindBy(id = "new_password_again-newPassword-passwordReset")
    private WebElement newPasswordRetypeField;

    @FindBy(id = "reset")
    private WebElement submitButton;

    String newPassword = "qwertyQ1";

    /**
     * Construct of the current page initialised web driver and page factory
     * @param driver - initialised driver of the parents class
     */
    public LinkedinCreateNewPasswordPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * verifying if the specific web page is loaded
     * @return true if page loaded and false - if not
     */
    public boolean isLoaded() {
        boolean isLoaded = false;
        try {
            isLoaded = submitButton.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * Fulfills two mandatory fields with new password and clicks on submit
     * @param newPassword - new password to be entered in both fields
     * @return next page that appears after submitting
     */
    public LinkedinPasswordSuccessfullyChangedPage resetPassword (String newPassword) {
        newPasswordField.sendKeys(newPassword);
        newPasswordRetypeField.sendKeys(newPassword);
        waitUntilElementIsClickable(submitButton).click();
        return new LinkedinPasswordSuccessfullyChangedPage(driver);
    }
}
