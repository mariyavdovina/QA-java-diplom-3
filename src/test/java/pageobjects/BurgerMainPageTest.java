package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
@RunWith(Parameterized.class)
public class BurgerMainPageTest {
    private static WebDriver driver ;
    private final By button;
    private BurgerMainPage objBurgerMainPage;
    private final String name;
    private final String pwd;

    @Before
    public void setUp(){
        driver =new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
    }
    public BurgerMainPageTest(By button, String name, String pwd){
        this.button = button;
        this.name = name;
        this.pwd = pwd;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {By.xpath("//div/header/nav/a"), "name103@email.domain", "123456"},
                {By.xpath("//button[text()='Войти в аккаунт']"), "name103@email.domain", "123456"}
        };
    }
    @Test
    @Description("Check login is available from main page")
    public void testLoginPossibleFromMainPage(){
        objBurgerMainPage.login(button,name,pwd);
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}