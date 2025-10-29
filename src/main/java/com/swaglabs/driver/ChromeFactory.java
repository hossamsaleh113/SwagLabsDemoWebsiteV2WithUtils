package com.swaglabs.driver;

import com.swaglabs.utils.LogsManager;
import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ChromeFactory extends AbstractDriver implements DriverOptionsAbstract<ChromeOptions> {


    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-extensions");
        Map<String, Object> prefs = new HashMap<>();
        String userDir = System.getProperty("user.dir");
        String downloadPath = userDir + "\\src\\test\\resources\\downloads";
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory",downloadPath);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        options.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
        options.setAcceptInsecureCerts(true);
//        options.addExtensions(haramBlurExtension);
        switch (PropertiesUtils.getProperty("executionType"))
        {
            case "LocalHeadless" -> options.addArguments("--headless");
            case  "Remote" ->
            {
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--headless=new");
            }
        }
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    @Override
    public WebDriver startDriver() {
        if (PropertiesUtils.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertiesUtils.getProperty("executionType").equalsIgnoreCase("LocalHeadless")) {
            return new ChromeDriver(getOptions());
        } else if (PropertiesUtils.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://" + remoteHost + ":" + remotePort + "/wd/hub").toURL(), getOptions()
                );
            } catch (Exception e) {
                LogsManager.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        } else {
            LogsManager.error("Invalid execution type: " + PropertiesUtils.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertiesUtils.getProperty("executionType"));
        }
    }
}


