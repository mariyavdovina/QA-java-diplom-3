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

public class ProfilePageTest {
    private WebDriver driver;
    private BurgerMainPage objBurgerMainPage;
    private UserClient userClient;
    private User user;
    private String accessToken;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
        userClient = new UserClient();
        user = UserProvider.getRandom();
        ValidatableResponse responseCreate = userClient.create(user);
        responseCreate.assertThat().statusCode(SC_OK);
        ValidatableResponse responseLogin = userClient.login(Credentials.from(user));
        int statusCode = responseLogin.extract().statusCode();
        accessToken = responseLogin.extract().path("accessToken").toString().substring(6).trim();
        Assert.assertEquals(SC_OK, statusCode);
        objBurgerMainPage.login(objBurgerMainPage.getLoginButton(),user.getEmail(),user.getPassword());
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
    }
    @Test
    @Description("Profile page can be opened")
    public void profileCanBeOpened(){
        driver.findElement(objBurgerMainPage.getProfile()).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']"))).getText();
        Assert.assertEquals("Профиль",isOpened);
    }
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        userClient.delete(accessToken);
        Thread.sleep(300);
    }
}
