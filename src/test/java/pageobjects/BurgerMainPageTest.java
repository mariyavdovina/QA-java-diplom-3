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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import providers.UserProvider;
import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class BurgerMainPageTest {
    private static WebDriver driver ;
    private final By button;
    private static BurgerMainPage objBurgerMainPage;
    private final String name;
    private final String pwd;
    private static UserClient userClient;
    private static User user;
    private static String accessToken;
    @Before
    public void setUp(){
        objBurgerMainPage = new BurgerMainPage(driver);
    }
    public BurgerMainPageTest(By button, String name, String pwd){
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        this.button = button;
        this.name = name;
        this.pwd = pwd;
    }
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        BurgerMainPage tmp = new BurgerMainPage(driver);
        userClient = new UserClient();
        user = UserProvider.getRandom();
        return new Object[][]{
                {tmp.profile, user.getEmail(), user.getPassword()},
                {tmp.loginButton, user.getEmail(), user.getPassword()}
        };
    }
    @Test
    @Description("Check login is available from main page")
    public void testLoginPossibleFromMainPage(){
        ValidatableResponse responseCreate = userClient.create(user);
        responseCreate.assertThat().statusCode(SC_OK);
        ValidatableResponse responseLogin = userClient.login(Credentials.from(user));
        int statusCode = responseLogin.extract().statusCode();
        accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
        Assert.assertEquals(SC_OK, statusCode);
        objBurgerMainPage.login(button,name,pwd);
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}