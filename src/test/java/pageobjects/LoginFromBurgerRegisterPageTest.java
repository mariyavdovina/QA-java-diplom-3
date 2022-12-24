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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import providers.UserProvider;

import static org.apache.http.HttpStatus.SC_OK;

public class LoginFromBurgerRegisterPageTest {
    private WebDriver driver;
    private BurgerRegisterPage objBurgerRegisterPage;
    private UserClient userClient;
    private User user;
    private String accessToken;
    private int statusCode;
    private ValidatableResponse responseLogin;
    private ValidatableResponse responseCreate;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        objBurgerRegisterPage = new BurgerRegisterPage(driver);
        userClient = new UserClient();
        user = UserProvider.getRandom();

    }
    @Test
    @Description("Check login is available from register page")
    public void testLoginPossible(){
        responseCreate = userClient.create(user);
        responseCreate.assertThat().statusCode(SC_OK);
        responseLogin = userClient.login(Credentials.from(user));
        statusCode = responseLogin.extract().statusCode();
        accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
        Assert.assertEquals(SC_OK, statusCode);
        objBurgerRegisterPage.login(objBurgerRegisterPage.getLoginButton(),user.getEmail(),user.getPassword());
        Assert.assertTrue(objBurgerRegisterPage.isLoggedIn());
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}