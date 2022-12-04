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

public class ProfilePageTest {
    private WebDriver driver;
    private BurgerMainPage objBurgerMainPage;
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");
        objBurgerMainPage = new BurgerMainPage(driver);
        objBurgerMainPage.login(objBurgerMainPage.getLoginButton(),"name103@email.domain","123456");
        Assert.assertTrue(objBurgerMainPage.isLoggedIn());
    }
    @Test
    @Description("Profile page can be opened")
    public void profileCanBeOpened(){
        driver.findElement(By.xpath("//div/header/nav/a")).click();
        String isOpened = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Профиль']"))).getText();
        Assert.assertEquals("Профиль",isOpened);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
