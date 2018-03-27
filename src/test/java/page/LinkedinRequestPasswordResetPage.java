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

    public LinkedinPasswordResetSubmitPage submitEmail(String userEmail) {
        userNameField.sendKeys("aashcherba.qa@gmail.com");
        submitButton.click();
        return new LinkedinPasswordResetSubmitPage (driver);
    }

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
