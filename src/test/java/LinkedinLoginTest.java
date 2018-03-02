import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void beforeTest(){
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }

    @Test
    public void successfulLoginTest() {
        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);

        String initialPageTitle = loginPage.getPageTitle();
        String initialPageUrl = loginPage.getPageUrl();

        Assert.assertEquals(initialPageTitle, "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");

        LinkedinBasePage homePage = loginPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");
        Assert.assertTrue(homePage.isSignedIn(), "User is not signed in");

        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle,
                "Page title did not change after login");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl,
                "Page url did not change after login");
    }

    @Test
    public void negativeLoginTest(){
        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
        loginPage.loginAs("aashcherba@bigmir.net");
        WebElement initialPageAlert = loginPage.getPageAlert();
        WebElement initialButton = loginPage.getButtonAppears();

        Assert.assertTrue(initialButton.isDisplayed(), "Alert message is not displayed.");
        Assert.assertTrue(initialPageAlert.isDisplayed(), "Alert message is not displayed.");
    }



}