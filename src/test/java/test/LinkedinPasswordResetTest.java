package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinPasswordResetSubmitPage;
import page.LinkedinRequestPasswordResetPage;
import utils.GMailService;


public class LinkedinPasswordResetTest extends LinkedinBaseTest {
    String userEmail = "aashcherba.qa@gmail.com";

    @Test
    public void successfulPasswordResetTest() {
       LinkedinRequestPasswordResetPage requestPasswordResetPage = landingPage.forgotPasswordLinkClick();
       Assert.assertTrue (requestPasswordResetPage.isLoaded(),
                "requestPasswordResetPage is not loaded");
        LinkedinPasswordResetSubmitPage requestPasswordSubmitPage = requestPasswordResetPage.submitEmail(userEmail);
        //Assert.assertTrue(requestPasswordSubmitPage.isLoaded(), "passwordResetSubmitPage is not loaded");

        String messageSubjectPartial = "here's the link to reset your password";
        String messageToPartial = "aashcherba.qa@gmail.com";
        String messageFromPartial = "security-noreply@linkedin.com";


        GMailService GMailService = new GMailService();
        String message = GMailService.waitForNewMessage(messageSubjectPartial, messageToPartial, messageFromPartial, 60);
        System.out.println("Content:" + message);

        String hyperLink = new String();
        for (String specificWordFromTheList : message.split(" ")) {
            if (specificWordFromTheList.contains("http")) {
                hyperLink = specificWordFromTheList;
                System.out.println("This text is hyperlink :" + hyperLink);
            }
        }

        WebElement linkToTheCreateNewPassPage = driver.findElement(By.linkText(hyperLink));
        linkToTheCreateNewPassPage.click();
        /*LinkedinCreateNewPasswordPage createNewPasswordPage = linkToTheCreateNewPassPage.click();
        LinkedinPasswordSuccessfullyChangedPage passwordChangedPage = createNewPasswordPage.resetPassword();
        LinkedinHomePage homePage = passwordChangedPage.goToTheMainPage();
        Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");
        */
    }
}
