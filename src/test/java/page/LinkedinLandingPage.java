package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class LinkedinLandingPage extends LinkedinBasePage{

    @FindBy(id = "login-email")
    private WebElement emailField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login-submit")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;


    public LinkedinLandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on the link to start changing of the password
     * @return first page of the changing password flow
     */
    public LinkedinRequestPasswordResetPage forgotPasswordLinkClick() {
        forgotPasswordLink.click();
        return new LinkedinRequestPasswordResetPage(driver);
    }

    /**
     * Log in to the system by entering credentials and clicking on SignIn button
     * @param email - email to enter
     * @param password - password to enter
     * @param <T> - the type of the arguments for that method that not specified
     *           (could be any non-primitive type)
     * @return nthe Home Page, LogIn Page or Landing Page
     * depending on URL address of the currently open web page
     */
    public <T> T loginAs(String email, String password){
        waitUntilElementIsClickable(emailField, 5);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        if (getPageUrl().contains("/feed")) {
            return (T) new LinkedinHomePage(driver);
        }
        if (getPageUrl().contains("/login-submit")) {
            return (T) new LinkedinLoginPage(driver) {
                @Override
                public boolean isLoaded() {
                    return false;
                }
            };
        }
        else {
            return (T) this;
        }
    }
}
