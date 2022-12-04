package pageobjects;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerRegisterPageTest {
    private final String name;
    private final String email;
    private final String pwd;
    private final String expected;
    private static final List<String> expecteds = List.of("Некорректный пароль", "Вход");
    private final WebDriver driver;
    private BurgerRegisterPage objBurgerRegisterPage;

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
        return new Object[][]{
                {"name", "email", "12345", expecteds.get(0)},
                {"name106", "name106@email.domain", "123456", expecteds.get(1)},
        };
    }
    @Test
    @Description("Checking registration with correct/incorrect password")
    public void testRegister() {
        objBurgerRegisterPage.register(name, email, pwd);
        Assert.assertEquals(objBurgerRegisterPage.waitForResultVisibility(expected), expected);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
