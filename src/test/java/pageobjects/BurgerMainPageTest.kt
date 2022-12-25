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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import providers.UserProvider

@RunWith(Parameterized::class)
class BurgerMainPageTest(private var button: By, private var name: String, private var pwd: String) {
    init {
        driver = ChromeDriver()
        driver.get("https://stellarburgers.nomoreparties.site/")
    }
    @Before
    fun setUp() {
        page = BurgerMainPage(driver)
    }

    companion object {
        private lateinit var driver: WebDriver
        private lateinit var page: BurgerMainPage
        private lateinit var userClient: UserClient
        private lateinit var user: User
        private lateinit var accessToken: String
        @JvmStatic
        @Parameterized.Parameters
        fun testData(): Collection<Array<Any>> {
            userClient = UserClient()
            user = UserProvider.getRandom()
            return listOf(arrayOf(BurgerMainPage.profileButtonAuth, user.email, user.password), arrayOf(BurgerMainPage.loginButtonAuth, user.email, user.password))
        }
    }
    @Test
    @Description("Check login is available from main page")
    fun testLoginPossibleFromMainPage() {
        val responseCreate: ValidatableResponse = userClient.create(user)
        responseCreate.assertThat().statusCode(SC_OK)
        val responseLogin: ValidatableResponse = userClient.login(from(user))
        val statusCode = responseLogin.extract().statusCode()
        accessToken = userClient.extractToken(from(user))
        Assert.assertEquals(SC_OK, statusCode)
        page.login(button, name, pwd)
        Assert.assertTrue(page.isLoggedIn())
    }
    @After
    fun tearDown() {
        driver.quit()
        userClient.delete(accessToken)
        Thread.sleep(300)
    }
}