package com.swaglabs.driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Map;

public class EdgeFactory extends AbstractDriver implements DriverOptionsAbstract<EdgeOptions>{
    @Override
    public WebDriver startDriver() {
        return new EdgeDriver(getOptions());
    }

    @Override
    public EdgeOptions getOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-web-security");
        edgeOptions.addArguments("--allow-running-insecure-content");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--disable-popup-blocking");
        Map<String, Object> edgepref = Map.of("profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false);
        edgeOptions.setExperimentalOption("prefs", edgepref);
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return edgeOptions;
    }
}
