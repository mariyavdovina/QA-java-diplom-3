package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerRegisterPage {
    //Predefined user credentials "name103", "name103@email.domain", "123456"
    public WebDriver driver;
    public By firstNameField = By.xpath("//fieldset[1]/div/div/input");
    public By emailField = By.xpath("//fieldset[2]/div/div/input");
    public By passwordField = By.xpath("//fieldset[3]/div/div/input");
    public By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    public By loginButton = By.xpath("//a[text()='Войти']");
    public String waitForResultVisibility(String result) {
        return new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[text()='%s']", result)))).getText();
    }
    public void login(By button,String email, String pwd){
        driver.findElement(button).click();
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Войти']")));
        driver.findElement(By.xpath("//fieldset[1]/div/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//fieldset[2]/div/div/input")).sendKeys(pwd);
        driver.findElement(By.xpath("//button[text()='Войти']")).click();
    }
    public boolean isLoggedIn(){
        String isLogin = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Соберите бургер']"))).getText();
        return isLogin.equals("Соберите бургер");
    }
    public void register(String firstName, String email, String password){
        typeFirstName(firstName);
        typeEmail(email);
        typePassword(password);
        pressRegister();
    }
    public BurgerRegisterPage(WebDriver driver){
        this.driver = driver;
    }
    public void typeFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    public void typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void pressRegister() {
        driver.findElement(registerButton).click();
    }
}