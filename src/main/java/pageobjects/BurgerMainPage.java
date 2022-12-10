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
    private By profile = By.xpath("//div/header/nav/a");
    //private By sauceSection = By.xpath("//*[text()='Соус с шипами Антарианского плоскоходца']");
    private By sauceSection = By.xpath("//*/div/main/section[1]/div[2]/h2[2]");
    //private By bunSection = By.xpath("//*[text()='Краторная булка N-200i']");
    private By bunSection = By.xpath("//*/div/main/section[1]/div[2]/h2[1]");
    //private By fillerSection = By.xpath("//*[text()='Филе Люминесцентного тетраодонтимформа']");
    private By fillerSection = By.xpath("//*/div/main/section[1]/div[2]/h2[3]");
    private By constructor = By.xpath("//*/div/header/nav/ul/li[1]/a");
    private By logout = By.xpath("//*[text()='Выход']");
    private By logo = By.xpath("//div/a[@href='/']");
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
    public By getProfile() {
        return profile;
    }
    public void setProfile(By profile) {
        this.profile = profile;
    }
    public By getSauceSection() {
        return sauceSection;
    }
    public void setSauceSection(By sauceSection) {
        this.sauceSection = sauceSection;
    }
    public By getBunSection() {
        return bunSection;
    }
    public void setBunSection(By bunSection) {
        this.bunSection = bunSection;
    }
    public By getFillerSection() {
        return fillerSection;
    }
    public void setFillerSection(By fillerSection) {
        this.fillerSection = fillerSection;
    }
    public By getLogout() {
        return logout;
    }
    public void setLogout(By logout) {
        this.logout = logout;
    }
    public By getConstructor() {
        return constructor;
    }
    public void setConstructor(By constructor) {
        this.constructor = constructor;
    }
    public By getLogo() {
        return logo;
    }
    public void setLogo(By logo) {
        this.logo = logo;
    }
}
