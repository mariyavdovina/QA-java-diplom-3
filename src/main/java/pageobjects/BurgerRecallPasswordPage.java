package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class BurgerRecallPasswordPage {
    private WebDriver driver;
    public BurgerRecallPasswordPage(WebDriver driver){
        this.driver = driver;
    }
    private By loginButton = By.xpath("//a[text()='Войти']");
    public void login(String email, String pwd){
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Войти']")));
        driver.findElement(By.xpath("//fieldset[1]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//fieldset[2]/div/div/input")).sendKeys(pwd);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }

    public By getLoginButton() {
        return loginButton;
    }
}
