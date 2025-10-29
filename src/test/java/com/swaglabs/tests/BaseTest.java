package com.swaglabs.tests;

import com.swaglabs.driver.GUI_Driver;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.JsonUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

public class BaseTest {
    protected GUI_Driver driver;
    protected JsonUtils testData;
    protected String FIRST_NAME;
    protected String LAST_NAME;

    /**
     * Setup - runs before each test class
     * Browser is passed from TestNG XML
     */
    @BeforeClass
    @Parameters("browser")
    public void beforeClass(@Optional("chrome") String browserName) {
        // Load test data
        testData = new JsonUtils("test-data");
        FIRST_NAME = testData.getJsonData("checkout-information.firstName") + getTimeStamp();
        LAST_NAME = testData.getJsonData("checkout-information.lastName") + getTimeStamp();

        // Initialize driver for THIS browser only
        driver = new GUI_Driver(browserName);

        // Navigate to login page
        new LoginPage(driver).navigateToPage();
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing browser on thread: " + Thread.currentThread().getId());
        if (driver != null) {
            driver.browser().closeBrowser();
            GUI_Driver.quit(); // Clean up ThreadLocal
        }
    }
}
