package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;
import page.LinkedinSearchPage;

import java.util.List;

public class LinkedinSearchTest extends LinkedinBaseTest {

    @Test
    public void basicSearchTest(){
        String searchTerm = "hr";
//page Factory:
        LinkedinLandingPage loginPage = new LinkedinLandingPage(driver);
        LinkedinHomePage homePage = loginPage.loginAs("aashcherba@bigmir.net", "qwertyQ1");
        LinkedinSearchPage searchPage = homePage.searchByTerm(searchTerm);
        List<String> results = searchPage.getResults();

        Assert.assertEquals(results.size(), 10, "Number of results is wrong");
        for(String result: results) {
            Assert.assertTrue(result.toLowerCase().contains("hr"),
                    "Searchterm "+"'hr'"+ " not found in cart");
        }
    }

}
