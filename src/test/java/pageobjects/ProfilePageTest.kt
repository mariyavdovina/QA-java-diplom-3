package pageobjects

import clients.UserClient
import io.qameta.allure.Description
import io.restassured.response.ValidatableResponse
import models.Constants.Companion.MAIN
import models.Credentials
import models.User
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import providers.UserProvider

class ProfilePageTest {
    private lateinit var driver: WebDriver
    private lateinit var page: BurgerMainPage
    private lateinit var userClient: UserClient
    private lateinit var user: User
    private lateinit var accessToken: String
    @Before
    fun setUp() {
        driver = ChromeDriver()
        driver.get(MAIN)
        page = BurgerMainPage(driver)

    }
    @Test
    @Description("Profile page can be opened")
    fun profileCanBeOpened(){
        userClient = UserClient()
        user = UserProvider.getRandom()
        var responseCreate: ValidatableResponse  = userClient.create(user)
        responseCreate.assertThat().statusCode(SC_OK)
        var responseLogin:ValidatableResponse  = userClient.login(Credentials.from(user))
        var statusCode: Int = responseLogin.extract().statusCode()
        accessToken = userClient.extractToken(Credentials.from(user))
        Assert.assertEquals(SC_OK, statusCode)
        page.login(page.loginButton,user.email,user.password)
        Assert.assertTrue(page.isLoggedIn())
        page.openProfile();
        Assert.assertEquals("Профиль",page.getProfileOpenedHeader())
    }
    @After
    fun tearDown() {
        driver.quit()
    }
}