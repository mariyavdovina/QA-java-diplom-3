package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    public void testMainPageSections() throws InterruptedException {
        driver.findElement(objBurgerMainPage.getSauce()).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objBurgerMainPage.getSauceSection()));
        //Thread.sleep(3000);
        //WebElement element1 = driver.findElement(objBurgerMainPage.getSauceSection());
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element1);
        Assert.assertTrue(driver.findElement(objBurgerMainPage.getSauceSection()).isDisplayed());
        driver.findElement(objBurgerMainPage.getBun()).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objBurgerMainPage.getBunSection()));
        //Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(objBurgerMainPage.getBunSection()).isDisplayed());
        driver.findElement(objBurgerMainPage.getFiller()).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objBurgerMainPage.getFillerSection()));
        Assert.assertTrue(driver.findElement(objBurgerMainPage.getFillerSection()).isDisplayed());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
