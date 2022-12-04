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

public class ProfileControlsTest {
    private WebDriver driver;
    private BurgerMainPage objBurgerMainPage;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
        objBurgerMainPage.login(objBurgerMainPage.getLoginButton(),"name103@email.domain","123456");
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
        driver.findElement(By.xpath("//div/header/nav/a")).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']")));
        Assert.assertEquals("Профиль",driver.findElement(By.xpath("//*[text()='Профиль']")).getText());
    }

    @Test
    @Description("Constructor section available")
    public void constructorCanBeOpened(){
        driver.findElement(By.xpath("//*/div/header/nav/ul/li[1]/a")).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        Assert.assertEquals("Соберите бургер",isOpened);
    }
    @Test
    @Description("Logo is clickable and leads to main page")
    public void mainPageCanBeOpened(){
        driver.findElement(By.xpath("//div/a[@href='/']")).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        Assert.assertEquals("Соберите бургер",isOpened);
    }
    @Test
    @Description("Logout is possible on profile page")
    public void logoutPossible(){
        driver.findElement(By.xpath("//*[text()='Выход']")).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Вход']"))).getText();
        Assert.assertEquals("Вход",isOpened);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
