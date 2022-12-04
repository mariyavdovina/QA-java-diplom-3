package pageobjects;

import jdk.jfr.Description;
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

public class BurgerLoginFromRecallPasswordTest {
    private WebDriver driver;
    private BurgerRecallPasswordPage objBurgerRecallPasswordPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        objBurgerRecallPasswordPage = new BurgerRecallPasswordPage(driver);
    }
    @Test
    @Description("Check login is available from restore password page")
    public void testLoginPossible(){
        objBurgerRecallPasswordPage.login("name103@email.domain", "123456");
        String isLogin = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        Assert.assertEquals("Соберите бургер",isLogin);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}