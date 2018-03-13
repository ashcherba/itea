package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LinkedinSearchResults extends LinkedinHomePage {
    @FindBy (xpath = "//li[contains(@class,'search-result__occluded-item')]")
    public List<WebElement> results;

    public String cardTitle;

    public LinkedinSearchResults(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean getCardTitle () {
        for (WebElement result : results) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", result);
            String cardTitle = result.getText().toLowerCase();
            System.out.println("XXXX");
            System.out.println(cardTitle);
        }
        return false;
    }
}
