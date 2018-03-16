package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;
import page.LinkedinLoginPage;

public class LinkedinLoginTest extends LinkedinBaseTest{
    String initialPageTitle;
    String initialPageUrl;


    @Test
    public void successfulLoginTest() {
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");

        LinkedinHomePage homePage = landingPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");
        Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");

        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title did not change after login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page url did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLanding() {
        return new Object[][]{
                {"",""}};
    }

    @Test(dataProvider= "negativeTestCredentialsIsReturnedToLanding")
    public void negativeTestCredentialsIsReturnedToLanding(String email, String password) {
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");
        //LinkedinLoginPage loginPage = landingPage.loginAs(email, password);

        landingPage = landingPage.loginAs(email, password);
        Assert.assertEquals(landingPage.getPageTitle(), initialPageTitle,
                "User is signed in");
    }


    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLogin() {
        return new Object[][]{
                {"xcsg","dff", "Укажите действительный адрес эл. почты.", "Пароль должен содержать не менее 6 символов."}};
    }

    @Test(dataProvider= "negativeTestCredentialsIsReturnedToLogin")
    public void negativeTestCredentialsIsReturnedToLogin(String email, String password, String emailMessage, String passMessage) {
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
        Assert.assertEquals(initialPageTitle, "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");
        LinkedinLoginPage loginPage = landingPage.loginAs(email, password);
        Assert.assertTrue(loginPage.isAlertShown(), "User is logged in");

        String actualEmailMessage = loginPage.emailError();
        String actualPassMessage = loginPage.passError();

        Assert.assertEquals(actualEmailMessage, emailMessage, "actual and expected are different");
        Assert.assertEquals(actualPassMessage, passMessage,  "actual and expected are different");

    }
}