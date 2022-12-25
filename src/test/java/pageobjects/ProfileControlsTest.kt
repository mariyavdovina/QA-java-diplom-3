package pageobjects

import clients.UserClient
import io.qameta.allure.Description
import io.restassured.response.ValidatableResponse
import models.Credentials
import models.User
import org.apache.http.HttpStatus.SC_OK
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import providers.UserProvider

class ProfileControlsTest {
    private lateinit var driver: WebDriver
    private lateinit var page: BurgerMainPage
    private lateinit var userClient: UserClient
    private lateinit var user: User
    private lateinit var accessToken: String
    private var statusCode: Int =0
    private lateinit var responseLogin: ValidatableResponse
    private lateinit var responseCreate: ValidatableResponse

    @Before
    fun setUp() {
        driver = ChromeDriver()
        driver.get("https://stellarburgers.nomoreparties.site/")
        page = BurgerMainPage(driver)
        userClient = UserClient()
        user = UserProvider.getRandom()
        responseCreate = userClient.create(user)
        Thread.sleep(300)
        responseCreate.assertThat().statusCode(SC_OK)
        Thread.sleep(300)
        responseLogin = userClient.login(Credentials.from(user))
        Thread.sleep(300)
        statusCode = responseLogin.extract().statusCode()
        Thread.sleep(300)
        Assert.assertEquals(SC_OK,statusCode)
        accessToken=userClient.extractToken(Credentials.from(user))

    }
    @Test
    @Description("Constructor section available")
    fun constructorCanBeOpened(){
        page.login(page.loginButton,user.email,user.password)
        Assert.assertTrue(page.isLoggedIn())
        page.openProfile()
        Assert.assertEquals("Профиль",page.getProfileOpenedHeader())
        page.openConstructor()
        Assert.assertEquals("Соберите бургер",page.getConstructorOpenedHeader())
    }
    @Test
    @Description("Logo is clickable and leads to main page")
    fun mainPageCanBeOpened(){
        page.login(page.loginButton,user.email,user.password)
        Assert.assertTrue(page.isLoggedIn())
        page.openProfile()
        Assert.assertEquals("Профиль",page.getProfileOpenedHeader())
        page.openMainPage()
        Assert.assertEquals("Соберите бургер",page.getConstructorOpenedHeader())
    }
    @Test
    @Description("Logout is possible on profile page")
    fun logoutPossible(){
        page.login(page.loginButton,user.email,user.password)
        Assert.assertTrue(page.isLoggedIn())
        page.openProfile()
        Assert.assertEquals("Профиль",page.getProfileOpenedHeader())
        page.logoutMainPage()
        Assert.assertEquals("Вход",page.logoutHeader())
    }
    @After
    fun tearDown() {
        driver.quit()
        userClient.delete(accessToken)
        Thread.sleep(300)
    }
}