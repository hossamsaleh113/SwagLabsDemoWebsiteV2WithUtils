package com.swaglabs.tests;

import com.swaglabs.listeners.TestNGListeners;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class test extends BaseTest {
    @Test
    public void sampleTest(){
        System.out.println(driver.element().getText(By.tagName("h4")));
    }
}
