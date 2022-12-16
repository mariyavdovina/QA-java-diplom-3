package pageobjects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    //All product sections are available on main page
    public void testMainPageSections() throws InterruptedException {
        String current = driver.findElement(By.className("tab_tab_type_current__2BEPc")).getText();
        int tmp = objBurgerMainPage.sectionNames.indexOf(current);
        for(int i = 0; i<objBurgerMainPage.sectionNames.size();i++){
            if (i != tmp){
                driver.findElement(objBurgerMainPage.sectionCaptions.get(i)).click();
                new WebDriverWait(driver, Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(objBurgerMainPage.sections.get(i)));
                Assert.assertTrue(driver.findElement(objBurgerMainPage.sections.get(i)).isDisplayed());
            }
        }
        driver.findElement(objBurgerMainPage.sectionCaptions.get(tmp)).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objBurgerMainPage.sections.get(tmp)));
        Assert.assertTrue(driver.findElement(objBurgerMainPage.sections.get(tmp)).isDisplayed());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
