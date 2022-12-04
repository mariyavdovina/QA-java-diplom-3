package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageSectionsTest {
    private static WebDriver driver ;
    private BurgerMainPage objBurgerMainPage;
    private String check;
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
        check = objBurgerMainPage.returnLoadResult(By.xpath("//*[text()='Соус с шипами Антарианского плоскоходца']"));
        Assert.assertEquals("Соус с шипами Антарианского плоскоходца",check);
        driver.findElement(objBurgerMainPage.getBun()).click();
        check = objBurgerMainPage.returnLoadResult(By.xpath("//*[text()='Краторная булка N-200i']"));
        Assert.assertEquals("Краторная булка N-200i",check);
        driver.findElement(objBurgerMainPage.getFiller()).click();
        check = objBurgerMainPage.returnLoadResult(By.xpath("//*[text()='Филе Люминесцентного тетраодонтимформа']"));
        Assert.assertEquals("Филе Люминесцентного тетраодонтимформа",check);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
