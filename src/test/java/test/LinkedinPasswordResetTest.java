package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinCreateNewPasswordPage;
import page.LinkedinPasswordResetSubmitPage;
import page.LinkedinPasswordSuccessfullyChangedPage;
import page.LinkedinRequestPasswordResetPage;


public class LinkedinPasswordResetTest extends LinkedinBaseTest {
    String userEmail = "aashcherba.qa@gmail.com";
    String newPassword = "Stanislav123";


    @Test
    public void successfulPasswordResetTest() {
       LinkedinRequestPasswordResetPage requestPasswordResetPage = landingPage.forgotPasswordLinkClick();
       Assert.assertTrue (requestPasswordResetPage.isLoaded(),
                "requestPasswordResetPage is not loaded");

       LinkedinPasswordResetSubmitPage requestPasswordSubmitPage = requestPasswordResetPage.submitEmail(userEmail);
       String resetPasswordLink = requestPasswordSubmitPage.getResetPasswordLinkFromEmail(userEmail);
       Assert.assertTrue(requestPasswordSubmitPage.isLoaded(), "Page isn't loaded");

       LinkedinCreateNewPasswordPage createNewPasswordPage = requestPasswordSubmitPage.navigateToResetPasswordLink(resetPasswordLink);
       Assert.assertTrue(createNewPasswordPage.isLoaded(), "Page isn't loaded");

       LinkedinPasswordSuccessfullyChangedPage passwordChangedPage = createNewPasswordPage.resetPassword(newPassword);
       Assert.assertTrue(passwordChangedPage.isLoaded(), "Page isn't loaded");
    }
}
