package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LinkedinHomePage extends LinkedinBasePage{
    @FindBy(id = "profile-nav-item")
    private WebElement userIcon;

    @FindBy(xpath = "//input[@placeholder='Поиск']")
    private WebElement searchField;

    @FindBy (xpath = "//*[@type='search-icon']")
    private WebElement searchIcon;

    /**
     * Constructor of LinkedinHomePage class that takes WebDriver instance from LinkedinBasePage class
     * and initialise LinkedinHomePage WebElements via PageFactory.
     * @param driver - WebDriver instance that was initialised on BasePage
     */
    public LinkedinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * verifies if user logged in successfully by checking the User Icon element
     * @return true if User Icon appears on the web page
     */
    public boolean isSignedIn() {
        waitUntilElementIsClickable(userIcon);
        return userIcon.isDisplayed();
    }

    /**
     * Searches for elements by entered specific search term
     * @param searchTerm - text to search for
     * @return page with search results
     */
    public LinkedinSearchPage searchByTerm (String searchTerm) {
        waitUntilElementIsClickable(searchField);
        searchField.sendKeys(searchTerm);
        searchIcon.click();
        return new LinkedinSearchPage(driver);
    }

    @Override
    public boolean isLoaded() {
        return false;
    }
}