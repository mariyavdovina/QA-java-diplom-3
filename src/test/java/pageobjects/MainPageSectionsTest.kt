package pageobjects

import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class MainPageSectionsTest {
    private lateinit var driver: WebDriver
    private lateinit var page: BurgerMainPage
    @Before
    fun setUp() {
        driver = ChromeDriver()
        driver.get("https://stellarburgers.nomoreparties.site/")
        page = BurgerMainPage(driver)
    }

    @Test //All product sections are available on main page
    fun testMainPageSections() {
        val tmp: Int = page.identifySection()
        for (i in 0 until page.sectionsQuantity) {
            if (i != tmp) {
                page.waitForNSectionVisibility(i)
                assertTrue(driver.findElement(page.returnNSection(i)).isDisplayed)
            }
        }
        page.waitForNSectionVisibility(tmp)
        assertTrue(driver.findElement(page.returnNSection(tmp)).isDisplayed)
    }
    @After
    fun tearDown() {
        driver.quit()
    }
}