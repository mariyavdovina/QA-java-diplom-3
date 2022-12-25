package pageobjects

import clients.UserClient
import io.restassured.response.ValidatableResponse
import jdk.jfr.Description
import models.Credentials.Companion.from
import models.User
import org.apache.http.HttpStatus.SC_OK
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import providers.UserProvider

class LoginFromBurgerRecallPasswordTest {
    private lateinit var driver: WebDriver
    private lateinit var page: BurgerRecallPasswordPage
    private lateinit var userClient: UserClient
    private lateinit var user: User
    private lateinit var accessToken: String

    @Before
    fun setUp() {
        driver = ChromeDriver()
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password")
        page = BurgerRecallPasswordPage(driver)
        userClient = UserClient()
        user = UserProvider.getRandom()
    }

    @Test
    @Description("Check login is available from restore password page")
    fun testLoginPossible() {
        val responseCreate: ValidatableResponse = userClient.create(user)
        responseCreate.assertThat().statusCode(SC_OK)
        val responseLogin: ValidatableResponse = userClient.login(from(user))
        val statusCode: Int = responseLogin.extract().statusCode()
        accessToken=userClient.extractToken(from(user))
        Assert.assertEquals(SC_OK, statusCode)
        page.login(user.email, user.password)
        Assert.assertEquals("Соберите бургер", page.getConstructorOpenedHeader())
    }
    @After
    fun tearDown() {
        driver.quit()
        userClient.delete(accessToken)
        Thread.sleep(300)
    }
}