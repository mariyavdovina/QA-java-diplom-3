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

public class LoginFromBurgerRecallPasswordTest {
    private WebDriver driver;
    private BurgerRecallPasswordPage objBurgerRecallPasswordPage;
    private UserClient userClient;
    private User user;
    private String accessToken;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        objBurgerRecallPasswordPage = new BurgerRecallPasswordPage(driver);
        userClient = new UserClient();
        user = UserProvider.getRandom();
    }
    @Test
    @Description("Check login is available from restore password page")
    public void testLoginPossible(){
        ValidatableResponse responseCreate = userClient.create(user);
        responseCreate.assertThat().statusCode(SC_OK);
        ValidatableResponse responseLogin = userClient.login(Credentials.from(user));
        int statusCode = responseLogin.extract().statusCode();
        accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
        Assert.assertEquals(SC_OK, statusCode);
        objBurgerRecallPasswordPage.login(user.getEmail(),user.getPassword());
        Assert.assertEquals("Соберите бургер",  objBurgerRecallPasswordPage.getConstructorOpenedHeader());
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}