package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinCreateNewPasswordPage extends LinkedinBasePage{
    @FindBy(id = "new_password-newPassword-passwordReset")
    private WebElement newPasswordField;

    @FindBy(id = "new_password_again-newPassword-passwordReset")
    private WebElement newPasswordAgainField;

    @FindBy(id = "reset")
    private WebElement resetButton;

    String newPassword = "qwertyQ1";

    public LinkedinCreateNewPasswordPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LinkedinPasswordSuccessfullyChangedPage resetPassword () {
        newPasswordField.sendKeys(newPassword);
        newPasswordAgainField.sendKeys(newPassword);
        resetButton.click();
        return new LinkedinPasswordSuccessfullyChangedPage(driver);
    }
}
