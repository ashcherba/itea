package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LinkedinLoginPage extends LinkedinBasePage {

        @FindBy(id = "session_key-login")
        private WebElement emailField;

        @FindBy(id = "session_password-login")
        private WebElement passwordField;

        @FindBy(id = "btn-primary")
        private WebElement signInButton;

        @FindBy(xpath = "//*[@class='alert error']//strong")
        private WebElement alertMessage;

        @FindBy(id="session_key-login-error")
        private WebElement errorOnEmail;

        @FindBy(id = "session_password-login-error")
        private WebElement errorOnPass;

    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    /**
     * verifies if alert is displayed
     * @return true if alert appears and false - if not
     */
    public boolean isAlertShown(){
        waitUntilElementIsClickable(alertMessage);
        return alertMessage.isDisplayed();
    }

    /**
     * get text of the error message
     * @return the text of the error about the invalid email address
     */
    public String emailError(){
        try {
            waitUntilElementIsClickable(errorOnEmail, 5);
        } catch (Exception e){
            System.out.println("There is no error message");
        }
        return errorOnEmail.getText();
    }

    /**
     * get text of the error message
     * @return the text of the error about the invalid email passord
     */
    public String passError(){
        try {
            waitUntilElementIsClickable(errorOnPass, 5);
        } catch (Exception e) {
            System.out.println("There is no error message");
        }
        return errorOnPass.getText();
    }
    @Override
    public boolean isLoaded() {
        return false;
    }

}
