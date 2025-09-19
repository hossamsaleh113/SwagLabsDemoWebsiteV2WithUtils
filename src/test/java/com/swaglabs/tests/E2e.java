package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

@Listeners(TestNGListeners.class)
public class E2e {
    private WebDriver driver;
    private JsonUtils testData;
    String FIRST_NAME;
    String LAST_NAME;


    //Tests
    @Test
    public void testSuccessfulLogin() {
        new LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username")).
                enterPassword(testData.getJsonData("login-credentials.password")).
                clickLogin().assertSuccessfullLogIn();
    }

    @Test(dependsOnMethods = "testSuccessfulLogin")
    public void testAddItemToCart() {
        new HomePage(driver).addSpecificItemToCart(testData.getJsonData("products-list.item2.name")).
                assertItemAddedToCart(testData.getJsonData("products-list.item2.name"));
    }

    @Test(dependsOnMethods = "testAddItemToCart")
    public void testCheckoutProduct() {
        new HomePage(driver).clickCartIcon().assertProductDetails(testData.getJsonData("products-list.item2.name"), testData.getJsonData("products-list.item2.price"));
    }

    @Test(dependsOnMethods = "testCheckoutProduct")
    public void testInformationPage() {
        new CartPage(driver).clickCheckout().fillInformation(FIRST_NAME, LAST_NAME,
                testData.getJsonData("checkout-information.zip")).assertInputInformation(FIRST_NAME, LAST_NAME,
                testData.getJsonData("checkout-information.zip"));
    }

    @Test(dependsOnMethods = "testInformationPage")
    public void testConfirmationMassage() {
        new InformationPage(driver).clickContinue().clickOnFinishButton().assertConfirmationMassage(System.getProperty("confirmationMassage"));
    }


    // Configurations


    @BeforeClass
    public void beforeClass() {
        testData = new JsonUtils("test-data");
        FIRST_NAME = testData.getJsonData("checkout-information.firstName") + getTimeStamp();
        LAST_NAME = testData.getJsonData("checkout-information.firstName") + getTimeStamp();
        String browserName = System.getProperty("browserType");
        driver = DriverManager.createInstance(browserName);
        new LoginPage(driver).navigateToPage();
    }

    @AfterClass
    public void tearDown() {
        BrowserActions.closeBrowser(driver);
    }


}


