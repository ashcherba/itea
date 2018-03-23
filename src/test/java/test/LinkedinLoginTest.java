package test;

import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;


public class LinkedinLoginTest extends LinkedinBaseTest{


    @DataProvider
    public Object[][] positiveLoginTest() {
        return new Object[][]{
                {"aashcherba@bigmir.net","qwertyQ1"},
                {"aashcherba@bigmir.net ","qwertyQ1"},
                {"AAShcherba@BIgmir.net","qwertyQ1"},
                {"AASHCHERBA@BIGMIR.NET","qwertyQ1"}};
    }

    @Test (dataProvider= "positiveLoginTest")
    public void successfulLoginTest(String email, String password) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");

        LinkedinHomePage homePage = landingPage.loginAs(email,password);
        Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");

        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title did not change after login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page url did not change after login");
    }

    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLanding() {
        return new Object[][]{
                {"",""},
                {"aashcherba@bigmir.net",""},
                {"","qwertyQ1"}};
    }

    @Test(dataProvider= "negativeTestCredentialsIsReturnedToLanding")
    public void negativeTestCredentialsIsReturnedToLanding(String email, String password) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        landingPage = landingPage.loginAs(email, password);
        Assert.assertEquals(landingPage.getPageTitle(), initialPageTitle,
                "User is signed in");
    }


    @DataProvider
    public Object[][] negativeTestCredentialsIsReturnedToLogin() {
        return new Object[][]{
                {"xcsg","dff", "Укажите действительный адрес эл. почты.", "Пароль должен содержать не менее 6 символов."},
                {"aashcherba@bigmir.net","dff", "", "Пароль должен содержать не менее 6 символов."},
                {"aashcherba@bigmirggg.net","qwertyQ1", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"aashcherbabigmirfffff.net","qwertyQ1", "Укажите действительный адрес эл. почты.", ""}};
    }

    @Test(dataProvider= "negativeTestCredentialsIsReturnedToLogin")
    public void negativeTestCredentialsIsReturnedToLogin(String email, String password, String emailMessage, String passMessage) {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");
        LinkedinLoginPage loginPage = landingPage.loginAs(email, password);
        Assert.assertTrue(loginPage.isAlertShown(), "User is logged in");

        String actualEmailMessage = loginPage.emailError();
        String actualPassMessage = loginPage.passError();

        Assert.assertEquals(actualEmailMessage, emailMessage, "actual and expected are different");
        Assert.assertEquals(actualPassMessage, passMessage,  "actual and expected are different");

    }
}