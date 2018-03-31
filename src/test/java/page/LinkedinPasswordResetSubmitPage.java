package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GMailService;

public class LinkedinPasswordResetSubmitPage extends LinkedinBasePage{

    @FindBy (xpath = "//a[@class='status-link btn-resend-link']")
    private WebElement resentButtonLink;

    public LinkedinPasswordResetSubmitPage (WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * verifies if web element is loaded
     * @return true if resent button is displayed and false - if not
     */
    public boolean isLoaded() {
        boolean isLoaded = false;
        try {
          isLoaded = resentButtonLink.isDisplayed();
        }
        catch (NoSuchElementException e) {
            isLoaded = false;
        }
        return isLoaded;
    }

    /**
     * click on the link from the mail
     * @param resetPasswordLink - link to be clicked on
     * @return Create new password page
     */
    public LinkedinCreateNewPasswordPage navigateToResetPasswordLink(String resetPasswordLink){
        driver.get(resetPasswordLink);
        return new LinkedinCreateNewPasswordPage(driver);
    }

    /**
     * get the link from the message about the Password Changing
     * @param messageToPartial - email address of the receiver
     * @return link to reset password
     */
    public String getResetPasswordLinkFromEmail(String messageToPartial) {
        String messageSubjectPartial = "here's the link to reset your password";
        String messageFromPartial = "security-noreply@linkedin.com";
        GMailService GMailService = new GMailService();
        String message = GMailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        System.out.println("Content:" + message);


        String resetPasswordLink = StringUtils.substringBetween(message,"browser:","This link").trim();
        return resetPasswordLink;
    }
}
