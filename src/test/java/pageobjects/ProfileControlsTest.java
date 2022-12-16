package pageobjects;

import clients.UserClient;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import models.Credentials;
import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import providers.UserProvider;

import java.time.Duration;

import static org.apache.http.HttpStatus.SC_OK;

public class ProfileControlsTest {
    private WebDriver driver;
    private BurgerMainPage objBurgerMainPage;
    private UserClient userClient;
    private User user;
    private String accessToken;
    private int statusCode;
    private ValidatableResponse responseLogin;
    private ValidatableResponse responseCreate;
    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
        userClient = new UserClient();
        user = UserProvider.getRandom();
        responseCreate = userClient.create(user);
        Thread.sleep(300);
        responseCreate.assertThat().statusCode(SC_OK);
        Thread.sleep(300);
        responseLogin = userClient.login(Credentials.from(user));
        Thread.sleep(300);
        statusCode = responseLogin.extract().statusCode();
        Thread.sleep(300);
        Assert.assertEquals(SC_OK, statusCode);
        accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
    }

    @Test
    @Description("Constructor section available")
    public void constructorCanBeOpened(){
        objBurgerMainPage.login(objBurgerMainPage.loginButton,user.getEmail(),user.getPassword());
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
        driver.findElement(objBurgerMainPage.profile).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']")));
        Assert.assertEquals("Профиль",driver.findElement(By.xpath("//*[text()='Профиль']")).getText());
        driver.findElement(objBurgerMainPage.constructor).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        Assert.assertEquals("Соберите бургер",isOpened);
    }
    @Test
    @Description("Logo is clickable and leads to main page")
    public void mainPageCanBeOpened(){
        objBurgerMainPage.login(objBurgerMainPage.loginButton,user.getEmail(),user.getPassword());
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
        driver.findElement(objBurgerMainPage.profile).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']")));
        Assert.assertEquals("Профиль",driver.findElement(By.xpath("//*[text()='Профиль']")).getText());
        driver.findElement(objBurgerMainPage.logo).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        Assert.assertEquals("Соберите бургер",isOpened);
    }
    @Test
    @Description("Logout is possible on profile page")
    public void logoutPossible(){
        objBurgerMainPage.login(objBurgerMainPage.loginButton,user.getEmail(),user.getPassword());
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
        driver.findElement(objBurgerMainPage.profile).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']")));
        Assert.assertEquals("Профиль",driver.findElement(By.xpath("//*[text()='Профиль']")).getText());
        driver.findElement(objBurgerMainPage.logout).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Вход']"))).getText();
        Assert.assertEquals("Вход",isOpened);
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}
