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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import providers.UserProvider



@RunWith(Parameterized::class)
class BurgerRegisterPageTest(private val name: String, private val email: String, private val pwd: String, private val expected: String) {
    init {
            driver = ChromeDriver()
            driver.get(REGISTER)
    }
    @Before
    fun setUp() {
        page = BurgerRegisterPage(driver)
    }
    companion object{
        private lateinit var driver: WebDriver
        private lateinit var page: BurgerRegisterPage
        private lateinit var userClient: UserClient
        private lateinit var user: User
        private lateinit var accessToken: String
        private val expecteds = listOf("Некорректный пароль", "Вход")
        @JvmStatic
        @Parameterized.Parameters
        fun testData(): Collection<Array<Any>> {
            userClient = UserClient()
            user = UserProvider.getRandom()
            return listOf(arrayOf("name", "email", "12345", expecteds[0]), arrayOf(user.name, user.email, user.password, expecteds[1]))
        }
    }

    @Test
    @Description("Checking registration with correct/incorrect password")
    fun testRegister() {
        page.register(name, email, pwd)
        val responseLogin: ValidatableResponse = userClient.login(from(user))
        val statusCode: Int = responseLogin.extract().statusCode()
        if (statusCode == SC_OK) {
            accessToken = userClient.extractToken(from(user))
        }
        Assert.assertEquals(page.waitForResultVisibility(expected), expected)
        if (statusCode == SC_OK) {
            userClient.delete(accessToken)
        }
    }
    @After
    fun tearDown() {
        driver.quit()
        Thread.sleep(300)
    }
}