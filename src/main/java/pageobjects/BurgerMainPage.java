package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BurgerMainPage {
    public WebDriver driver;
    public By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    public By profile = By.xpath("//div/header/nav/a");
    public By currentSection = By.className("tab_tab_type_current__2BEPc");
    public List<String> sectionNames = List.of("Булки","Соусы","Начинки");
    public List <By> sectionCaptions = List.of(By.xpath("//*[text()='Булки']"),
            By.xpath("//*[text()='Соусы']"),
            By.xpath("//*[text()='Начинки']"));
    public List <By> sections = List.of(By.xpath("//*/div/main/section[1]/div[2]/h2[1]"),
            By.xpath("//*/div/main/section[1]/div[2]/h2[2]"),
            By.xpath("//*/div/main/section[1]/div[2]/h2[3]"));
    public By constructor = By.xpath("//*/div/header/nav/ul/li[1]/a");
    public By logout = By.xpath("//*[text()='Выход']");
    public By logo = By.xpath("//div/a[@href='/']");
    public BurgerMainPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickLoginButton(By button){
        driver.findElement(button).click();
    }
    public void login(By button, String email, String pwd){
        clickLoginButton(button);
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Вход']")));
        driver.findElement(By.xpath("//fieldset[1]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//fieldset[2]/div/div/input")).sendKeys(pwd);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }
    public boolean isLoggedIn(){
        String isLogin = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        return isLogin.equals("Соберите бургер");
    }
}
