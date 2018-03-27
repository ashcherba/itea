package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinPasswordSuccessfullyChangedPage extends LinkedinBasePage{
    @FindBy(xpath = "//a[@class='btn-secondary-transparent']")
    private WebElement buttonToTheMainPage;

    public LinkedinPasswordSuccessfullyChangedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LinkedinHomePage goToTheMainPage() {
        buttonToTheMainPage.click();
        return new LinkedinHomePage (driver);
    }
}
