package pageobjects

import clients.UserClient
import io.restassured.response.ValidatableResponse
import jdk.jfr.Description
import models.Constants.Companion.REGISTER
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

class LoginFromBurgerRegisterPageTest {
    private lateinit var driver: WebDriver
    private lateinit var page: BurgerRegisterPage
    private lateinit var userClient: UserClient
    private lateinit var user: User
    private lateinit var accessToken: String
    private var statusCode: Int =0
    private lateinit var responseLogin: ValidatableResponse
    private lateinit var responseCreate: ValidatableResponse

    @Before
    fun setUp(){
        driver = ChromeDriver()
        driver.get(REGISTER)
        page = BurgerRegisterPage(driver)
        userClient = UserClient()
        user = UserProvider.getRandom()
    }

    @Test
    @Description("Check login is available from register page")
    fun testLoginPossible() {
        responseCreate = userClient.create(user)
        responseCreate.assertThat().statusCode(SC_OK)
        responseLogin = userClient.login(from(user))
        statusCode = responseLogin.extract().statusCode()
        accessToken=userClient.extractToken(from(user))
        Assert.assertEquals(SC_OK, statusCode)
        page.login(page.loginButton, user.email, user.password)
        Assert.assertTrue(page.isLoggedIn())
    }

    @After
    fun tearDown() {
        driver.quit()
        userClient.delete(accessToken)
        Thread.sleep(300)
    }
}