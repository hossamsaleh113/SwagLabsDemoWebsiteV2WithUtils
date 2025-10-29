package com.swaglabs.driver;

import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract class AbstractDriver {

    protected final String remoteHost = PropertiesUtils.getProperty("remoteHost");
    protected final String remotePort = PropertiesUtils.getProperty("remotePort");
    protected File haramBlurExtension = new File("src/main/resources/extensions/HaramBlur.crx");
    protected String downloadsPath = System.getProperty("user.dir") + "\\src\\test\\resources";

    public abstract WebDriver startDriver();
}
