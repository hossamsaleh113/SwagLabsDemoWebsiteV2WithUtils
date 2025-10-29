package com.swaglabs.driver;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URI;

public class SafariFactory extends AbstractDriver {
    public SafariOptions getOptions() {
        SafariOptions options = new SafariOptions();
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }


    @Override
    public WebDriver startDriver() {
        if (PropertiesUtils.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertiesUtils.getProperty("executionType").equalsIgnoreCase("LocalHeadless") )
        {
            return new SafariDriver(getOptions());
        }
        else if (PropertiesUtils.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://"+ remoteHost+ ":" +remotePort + "/wd/hub").toURL(), getOptions()
                );
            }
            catch (Exception e) {
                LogsManager.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        }
        else {
            LogsManager.error("Invalid execution type: " + PropertiesUtils.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertiesUtils.getProperty("executionType"));
        }
    }
}