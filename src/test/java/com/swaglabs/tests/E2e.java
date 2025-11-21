package com.swaglabs.tests;

import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.P04_CartPage;
import com.swaglabs.pages.P05_InformationPage;
import com.swaglabs.pages.P01_LoginPage;
import com.swaglabs.pages.P02_HomePage;
import org.testng.annotations.*;

@Listeners(TestNGListeners.class)
public class E2e extends BaseTest {

    //Tests
    @Test
    public void testSuccessfulLogin() {
        new P01_LoginPage(driver).enterUsername(testData.getJsonData("login-credentials.username")).
                enterPassword(testData.getJsonData("login-credentials.password")).
                clickLogin().assertSuccessfullLogIn();
    }

    @Test(dependsOnMethods = "testSuccessfulLogin")
    public void testAddItemToCart() {
        new P02_HomePage(driver).addSpecificItemToCart(testData.getJsonData("products-list.item2.name")).
                assertItemAddedToCart(testData.getJsonData("products-list.item2.name")).assertCartIconNumber("1");
    }

    @Test(dependsOnMethods = "testAddItemToCart")
    public void testCheckoutProduct() {
        new P02_HomePage(driver).clickCartIcon().assertProductDetails(testData.getJsonData("products-list.item2.name"), testData.getJsonData("products-list.item2.price"));
    }

    @Test(dependsOnMethods = "testCheckoutProduct")
    public void testInformationPage() {
        new P04_CartPage(driver).clickCheckout().fillInformation(FIRST_NAME, LAST_NAME,
                testData.getJsonData("checkout-information.zip")).assertInputInformation(FIRST_NAME, LAST_NAME,
                testData.getJsonData("checkout-information.zip"));
    }

    @Test(dependsOnMethods = "testInformationPage")
    public void testConfirmationMassage() {
        new P05_InformationPage(driver).clickContinue().clickOnFinishButton().assertConfirmationMassage(System.getProperty("confirmationMassage"));
    }



}


