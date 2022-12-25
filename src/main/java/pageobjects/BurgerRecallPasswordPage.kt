package pageobjects

import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class BurgerRecallPasswordPage(private val driver: WebDriver) : Page(driver) {
    val loginButton = xpath("//a[text()='Войти']")
    init {
        PageFactory.initElements(driver, this)
    }

    fun login(email: String, pwd: String) {
        driver.findElement(loginButton).click()
        wait(xpath("//*[text()='Войти']"))
        driver.findElement(xpath("//fieldset[1]/div/div/input")).sendKeys(email)
        driver.findElement(xpath("//fieldset[2]/div/div/input")).sendKeys(pwd)
        driver.findElement(xpath("//button[text()='Войти']")).click()
    }
    fun getConstructorOpenedHeader(): String {
        return wait(xpath("//*[text()='Соберите бургер']")).text
    }

}