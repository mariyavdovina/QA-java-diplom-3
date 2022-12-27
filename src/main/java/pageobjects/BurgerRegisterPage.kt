package pageobjects

import org.openqa.selenium.By
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class BurgerRegisterPage(private val driver: WebDriver) : Page(driver){
    private val firstNameField = xpath("//fieldset[1]/div/div/input")
    private val emailField = xpath("//fieldset[2]/div/div/input")
    private val passwordField = xpath("//fieldset[3]/div/div/input")
    private val registerButton = xpath("//button[text()='Зарегистрироваться']")
    val loginButton: By = xpath("//a[text()='Войти']")
    init {
        PageFactory.initElements(driver, this)
    }

    fun waitForResultVisibility(result: String): String {
        return wait(xpath(String.format("//*[text()='%s']", result))).text
    }
    private fun typeFirstName(firstName: String) {
        driver.findElement(firstNameField).sendKeys(firstName)
    }

    private fun typeEmail(email: String) {
        driver.findElement(emailField).sendKeys(email)
    }

    private fun typePassword(password: String) {
        driver.findElement(passwordField).sendKeys(password)
    }

    private fun pressRegister() {
        driver.findElement(registerButton).click()
    }
    fun register(firstName: String, email: String, password: String) {
        typeFirstName(firstName)
        typeEmail(email)
        typePassword(password)
        pressRegister()
    }
    fun login(button: By, email: String, pwd: String) {
        driver.findElement(button).click()
        wait(xpath("//*[text()='Войти']"))
        driver.findElement(xpath("//fieldset[1]/div/div/input")).sendKeys(email)
        driver.findElement(xpath("//fieldset[2]/div/div/input")).sendKeys(pwd)
        driver.findElement(xpath("//button[text()='Войти']")).click()
    }
    fun isLoggedIn(): Boolean {
        return wait(xpath("//*[text()='Соберите бургер']")).text == "Соберите бургер"
    }
}