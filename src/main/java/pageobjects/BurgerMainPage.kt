package pageobjects

import org.openqa.selenium.By
import org.openqa.selenium.By.xpath
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory


class BurgerMainPage(private val driver: WebDriver) : Page(driver) {
    companion object{
        val loginButtonAuth: By = xpath("//button[text()='Войти в аккаунт']")
        val profileButtonAuth: By = xpath("//div/header/nav/a")
    }

    val loginButton: By = xpath("//button[text()='Войти в аккаунт']")
    val profile: By = xpath("//div/header/nav/a")
    private val sectionNames = listOf("Булки", "Соусы", "Начинки")
    private val sectionCaptions = listOf(xpath("//*[text()='Булки']"),
            xpath("//*[text()='Соусы']"),
            xpath("//*[text()='Начинки']"))
    private val sections = listOf(xpath("//*/div/main/section[1]/div[2]/h2[1]"),
            xpath("//*/div/main/section[1]/div[2]/h2[2]"),
            xpath("//*/div/main/section[1]/div[2]/h2[3]"))

    val sectionsQuantity = sections.size
    private val constructor = xpath("//*/div/header/nav/ul/li[1]/a")
    private val logout = xpath("//*[text()='Выход']")
    private val logo = xpath("//div/a[@href='/']")
    init {
        PageFactory.initElements(driver,this)
    }
    fun openConstructor() {
        driver.findElement(constructor).click()
        wait(xpath("//*[text()='Соберите бургер']"))
    }

    fun getConstructorOpenedHeader(): String {
        return wait(xpath("//*[text()='Соберите бургер']")).text
    }

    fun openProfile() {
        driver.findElement(profile).click()
        wait(xpath("//*[text()='Профиль']"))
    }

    fun getProfileOpenedHeader(): String {
        return wait(xpath("//*[text()='Профиль']")).text
    }

    fun openMainPage() {
        driver.findElement(logo).click()
        wait(xpath("//*[text()='Соберите бургер']"))
    }

    fun logoutMainPage() {
        driver.findElement(logout).click()
        wait(xpath("//*[text()='Вход']"))
    }

    fun logoutHeader(): String {
        return wait(xpath("//*[text()='Вход']")).text
    }

    fun clickLoginButton(button: By?) {
        driver.findElement(button).click()
    }

    fun login(button: By?, email: String?, pwd: String?) {
        clickLoginButton(button)
        wait(xpath("//*[text()='Вход']"))
        driver.findElement(xpath("//fieldset[1]/div/div/input")).sendKeys(email)
        driver.findElement(xpath("//fieldset[2]/div/div/input")).sendKeys(pwd)
        driver.findElement(xpath("//button[text()='Войти']")).click()
    }

    fun isLoggedIn(): Boolean {
        return wait(xpath("//*[text()='Соберите бургер']")).text == "Соберите бургер"
    }

    fun identifySection(): Int {
        val current = driver.findElement(By.className("tab_tab_type_current__2BEPc")).text
        return sectionNames.indexOf(current)
    }

    fun waitForNSectionVisibility(n: Int) {
        driver.findElement(sectionCaptions[n]).click()
        wait(returnNSection(n))
    }

    fun returnNSection(n: Int): By {
        return sections[n]
    }


}

