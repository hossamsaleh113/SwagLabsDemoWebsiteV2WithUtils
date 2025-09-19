package com.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private static final String PROPERTIES_PATH = "src/main/resources/";

    private PropertiesUtils() {
        super();
    }

    public static void loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFileList = FileUtils.listFiles(new File(PROPERTIES_PATH) , new String[] {"properties"} , true );
            propertiesFileList.forEach(propertyFile -> {
                try {
                    properties.load(new FileInputStream(propertyFile));
                } catch (IOException e) {
                    LogsUtils.error(e.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties);
            });
            LogsUtils.info("Loading properties file data");

        } catch (Exception e) {
            LogsUtils.error("Failed to load properties file date because" + e.getMessage() );
        }
    }

    public static String getPropertyValue(String key){
        try {
            return System.getProperty(key);
        }catch (Exception e){
            LogsUtils.error(e.getMessage());
            return "";
        }
    }
}
