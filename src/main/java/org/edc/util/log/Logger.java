package org.edc.util.log;

/**
 * A simple logging class. This class probably inflates the jar size by about 1K to 1.5K. It is
 * probably OK.
 * 
 * @author greg
 */
public class Logger {

    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int ERROR = 3;

    private final int logLevel;
    private static Logger instance;

    public static void init(String logLevelName) {
        if (instance == null) {
            int logLevel = ERROR;
            if ("INFO".equals(logLevelName)) {
                logLevel = INFO;
            } else if ("DEBUG".equals(logLevelName)) {
                logLevel = DEBUG;
            }
            instance = new Logger(logLevel);
        }
    }

    private Logger(int logLevel) {
        this.logLevel = logLevel;
    }

    public static Logger getLogger() {
        return instance;
    }
    
    public void debug(Object origin, String message) {
        log(origin, DEBUG, message);
    }

    public void log(Class origin, int level, String message) {
        if (level >= this.logLevel) {
            System.out.println(logLevelPrefix(level) + origin.getName() + " -- " + message);
        }
    }

    public void log(Object origin, int level, String message) {
        log(origin.getClass(), level, message);
    }

    public boolean isDebug() {
        return this.logLevel == DEBUG;
    }

    public boolean isInfo() {
        return this.logLevel <= INFO;
    }

    static String logLevelPrefix(int level) {
        switch (level) {
            case DEBUG:
                return "[DEBUG]: ";
            case INFO:
                return "[INFO]: ";
            case ERROR:
                return "[ERROR]: ";
            default:
                break;
        }
        return null;
    }
}
