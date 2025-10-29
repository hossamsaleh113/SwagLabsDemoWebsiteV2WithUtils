package com.swaglabs.utils;


import com.swaglabs.utils.reports.AllureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsManager {

    public static final String LOG_PATH = "test-outputs/Logs";
    private LogsManager(){
        super();
    }

    private static Logger logger(){
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }
    public synchronized static void trace(String... massage){
        logger().trace(String.join(" " , massage));
    }

    public synchronized static void info(String... massage){
        logger().info(String.join(" " , massage));
    }

    public synchronized static void debug(String... massage){
        logger().debug(String.join(" " , massage));
    }

    public synchronized static void warn(String... massage){
        logger().warn(String.join(" " , massage));
    }

    public synchronized static void error(String... massage){
        logger().error(String.join(" " , massage));
    }

    public synchronized static void fatal(String... massage){
        logger().fatal(String.join(" " , massage));
    }

}
