package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerMainPage {
    private WebDriver driver;
    private By userHome = By.xpath("//div/header/nav/a");
    private By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By sauce = By.xpath("//*[text()='Соусы']");
    private By bun = By.xpath("//*[text()='Булки']");
    private By filler = By.xpath("//*[text()='Начинки']");

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
        String isLogin = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        return isLogin.equals("Соберите бургер");
    }
    public String returnLoadResult(By element){
        return new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    public WebDriver getDriver() {
        return driver;
    }
    public By getUserHome() {
        return userHome;
    }

    public By getLoginButton() {
        return loginButton;
    }
    public By getSauce() {
        return sauce;
    }
    public By getBun() {
        return bun;
    }
    public By getFiller() {
        return filler;
    }
}
