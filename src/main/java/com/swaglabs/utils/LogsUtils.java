package com.swaglabs.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {

    public static final String LOG_PATH = "test-outputs/Logs";
    private LogsUtils(){
        super();
    }

    public static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }
    public static void trace(String... massage){
        logger().trace(String.join(" " , massage));
    }

    public static void info(String... massage){
        logger().info(String.join(" " , massage));
    }

    public static void debug(String... massage){
        logger().debug(String.join(" " , massage));
    }

    public static void warn(String... massage){
        logger().warn(String.join(" " , massage));
    }

    public static void error(String... massage){
        logger().error(String.join(" " , massage));
    }

    public static void fatal(String... massage){
        logger().fatal(String.join(" " , massage));
    }

}
