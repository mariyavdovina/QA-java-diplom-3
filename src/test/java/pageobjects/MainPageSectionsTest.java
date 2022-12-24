package pageobjects;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
    //All product sections are available on main page
    public void testMainPageSections() {
        int tmp = objBurgerMainPage.identifySection();
        for(int i = 0; i<objBurgerMainPage.getSectionsQuantity();i++){
            if (i != tmp){
                objBurgerMainPage.waitForNSectionVisibility(i);
                Assert.assertTrue(driver.findElement(objBurgerMainPage.returnNSection(i)).isDisplayed());
            }
        }
        objBurgerMainPage.waitForNSectionVisibility(tmp);
        Assert.assertTrue(driver.findElement(objBurgerMainPage.returnNSection(tmp)).isDisplayed());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
