package com.swaglabs.utils.actions;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.Waits;
import org.openqa.selenium.WebDriver;

public class AlertActions {
    private final WebDriver driver;
    private final Waits wait;

    public AlertActions(WebDriver driver){
        this.driver = driver;
        wait = new Waits(driver);
    }

    public void acceptAlert(){
        wait.waitFor().until(d ->{
            try {
                d.switchTo().alert().accept();
                return true;

            } catch (Exception e) {
                return false;
            }
        });
    }

    public void dismissAlert(){
        wait.waitFor().until(d ->{
            try {
                d.switchTo().alert().dismiss();
                return true;

            } catch (Exception e) {
                return false;
            }
        });
    }


    public String getTextFromAlert(){
        return wait.waitFor().until(d -> {
            try {
                String text = d.switchTo().alert().getText();
                return (!text.isEmpty()) ? text : null;
            }catch (Exception e){
                return null;
            }
        });
    }


}
