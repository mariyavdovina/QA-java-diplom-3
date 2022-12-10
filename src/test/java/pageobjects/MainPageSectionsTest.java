package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageSectionsTest {
    private static WebDriver driver ;
    private BurgerMainPage objBurgerMainPage;

    @Before
    public void setUp(){
        driver =new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
    }
    @Test
    @Description("All product sections are available on main page")
    public void testMainPageSections(){
        driver.findElement(objBurgerMainPage.getSauce()).click();
        WebElement element1 = driver.findElement(objBurgerMainPage.getSauceSection());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element1);
        Boolean sauceVisible = driver.findElement(objBurgerMainPage.getSauceSection()).isDisplayed();
        Assert.assertTrue(sauceVisible);
        driver.findElement(objBurgerMainPage.getBun()).click();
        WebElement element2 = driver.findElement(objBurgerMainPage.getBunSection());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element2);
        Boolean bunVisible = driver.findElement(objBurgerMainPage.getBunSection()).isDisplayed();
        Assert.assertTrue(bunVisible);
        driver.findElement(objBurgerMainPage.getFiller()).click();
        WebElement element3 = driver.findElement(objBurgerMainPage.getFillerSection());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element3);
        Boolean fillerVisible = driver.findElement(objBurgerMainPage.getFillerSection()).isDisplayed();
        Assert.assertTrue(fillerVisible);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
