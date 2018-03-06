package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;

public class BadCodeExample {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello, world");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize(); //maximize the window of browser
        driver.get("https://www.google.com/");
        sleep(5000);
        driver.findElement(By.id("lst-ib")).sendKeys("Selenium");
        driver.findElement(By.name("btnK")).click();

        //find all links on the first search result page
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"rso\"]/div/div/div/div/div/h3/a"));
        System.out.println("Total number of search result links: "+ list.size());

        //find all links on the first search result page contain Selenium word
        List<WebElement> seList = driver.findElements(By.xpath("//*[@id=\"rso\"]/div/div/div/div/div/h3/a[contains(text(),'elenium')]"));
        System.out.println("Number of " + "Selenium " + "links: "+ seList.size());

        //verify that all search results include word "Selenium"
        if(list.size() == seList.size()){
            System.out.println("All search results contain word Selenium");
        } else {
            System.out.println("NOT All search results contain word Selenium");
        }
        /*for(WebElement element: list) {
               System.out.println(element.getText());
               assertTrue(element.getText().contains("Selenium"));
       }
       for(int i = 0; i < list.size(); i++){
           WebElement title = list.get(i);
           System.out.println(title.getText().contains("Selenium"));
           System.out.println(list.get(i));
           list(i).getText("Selenium"));
       }
       */
        driver.quit();
    }}
