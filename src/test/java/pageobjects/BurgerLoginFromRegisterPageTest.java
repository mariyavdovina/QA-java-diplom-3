package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BurgerLoginFromRegisterPageTest {
    private WebDriver driver;
    private BurgerRegisterPage objBurgerRegisterPage;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        objBurgerRegisterPage = new BurgerRegisterPage(driver);
    }
    @Test
    @Description("Check login is available from register page")
    public void testLoginPossible(){
        objBurgerRegisterPage.login(objBurgerRegisterPage.getLoginButton(),"name103@email.domain", "123456");
        //objBurgerRegisterPage.login(objBurgerRegisterPage.getUserHome(),"name103@email.domain", "123456");
        Assert.assertTrue(objBurgerRegisterPage.isLoggedIn());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}