package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinPasswordResetSubmitPage;
import page.LinkedinRequestPasswordResetPage;

public class LinkedinPasswordResetTest extends LinkedinBaseTest {
    String userEmail = "aashcherba@bigmir.net";

    @Test
    public void successfulPasswordResetTest() {
        LinkedinRequestPasswordResetPage requestPasswordResetPage = landingPage.forgotPasswordLinkClick();
        Assert.assertTrue (requestPasswordResetPage.isLoaded(),
                "requestPasswordResetPage is not loaded");

        LinkedinPasswordResetSubmitPage requestPasswordSubmitPage = requestPasswordResetPage.submitEmail(userEmail);
        Assert.assertTrue (requestPasswordSubmitPage.isLoaded(),
                "requestPasswordSubmitPage is not loaded");

                //read email
    }
}
