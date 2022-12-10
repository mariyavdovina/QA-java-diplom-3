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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import providers.UserProvider;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

@RunWith(Parameterized.class)
public class BurgerRegisterPageTest {
    private final String name;
    private final String email;
    private final String pwd;
    private final String expected;
    private static final List<String> expecteds = List.of("Некорректный пароль", "Вход");
    private final WebDriver driver;
    private BurgerRegisterPage objBurgerRegisterPage;
    private static UserClient userClient;
    private static User user;
    private static String accessToken;

    @Before
    public void setUp() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        objBurgerRegisterPage = new BurgerRegisterPage(driver);
    }

    public BurgerRegisterPageTest(String name, String email, String pwd, String expected) {
        driver = new ChromeDriver();
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        userClient = new UserClient();
        user = UserProvider.getRandom();
        return new Object[][]{
                {"name", "email", "12345", expecteds.get(0)},
                {user.getName(),user.getEmail(),user.getPassword(), expecteds.get(1)},
        };
    }
    @Test
    @Description("Checking registration with correct/incorrect password")
    public void testRegister() {
        objBurgerRegisterPage.register(name, email, pwd);
        ValidatableResponse responseLogin = userClient.login(Credentials.from(user));
        int statusCode = responseLogin.extract().statusCode();
        if(statusCode == SC_OK){
            accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
        }
        Assert.assertEquals(objBurgerRegisterPage.waitForResultVisibility(expected), expected);
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}
