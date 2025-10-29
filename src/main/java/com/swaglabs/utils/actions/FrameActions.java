package com.swaglabs.utils.actions;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameActions {
    private final WebDriver driver;
    private final Waits wait;

    public FrameActions(WebDriver driver){
        this.driver = driver;
        wait = new Waits(driver);
    }

    public void switchToFrameByIndex(int index){
        wait.waitFor().until(d-> {
            try {
                d.switchTo().frame(index);
                return true;
            }catch (Exception e){
                // Error logging for switchToFrameByIndex
                LogsManager.error("Failed to switch to frame by index: " + index, e.getMessage());
                return false;
            }
        });
    }


    public void switchToFrameByName(String name){
        wait.waitFor().until(d-> {
            try {
                d.switchTo().frame(name);
                return true;
            }catch (Exception e){
                // Error logging for switchToFrameByName
                LogsManager.error("Failed to switch to frame by name: " + name, e.getMessage());
                return false;
            }
        });
    }

    public void switchToFrameByElement(By locator){
        wait.waitFor().until(d-> {
            try {
                WebElement element = d.findElement(locator);
                d.switchTo().frame(element);
                return true;
            }catch (Exception e){
                // Error logging for switchToFrameByElement
                LogsManager.error("Failed to switch to frame by locator: " + locator.toString(), e.getMessage());
                return false;
            }
        });
    }


    public void switchToDefaultContent(){
        wait.waitFor().until(d-> {
            try {
                d.switchTo().defaultContent();
                return true;
            }catch (Exception e){
                // Error logging for switchToDefaultContent
                LogsManager.error("Failed to switch to default content (main page)", e.getMessage());
                return false;
            }
        });
    }

}