package net.ivica.reservations.api;

import org.apache.commons.logging.Log;

public class LogHelper {

    private LogHelper() {
    }

    public static void debug(Log log, String message) {
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }

    public static void debug(Log log, String message, Object... args) {
        if (log.isDebugEnabled()) {
            log.debug(String.format(message, args));
        }
    }

    public static void debug(Log log, String message, Throwable e) {
        if (log.isDebugEnabled()) {
            log.debug(message, e);
        }
    }

    public static void debug(Log log, String message, Throwable e, Object... args) {
        if (log.isDebugEnabled()) {
            log.debug(String.format(message, args), e);
        }
    }

    public static void error(Log log, String message) {
        log.error(message);
    }

    public static void error(Log log, String message, Object... args) {
        log.error(String.format(message, args));
    }

    public static void error(Log log, String message, Throwable e) {
        log.error(message, e);
    }

    public static void error(Log log, String message, Throwable e, Object... args) {
        log.error(String.format(message, args), e);
    }

    public static void info(Log log, String message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }

    public static void info(Log log, String message, Object... args) {
        if (log.isInfoEnabled()) {
            log.info(String.format(message, args));
        }
    }

    public static void trace(Log log, String message) {
        if (log.isTraceEnabled()) {
            log.trace(message);
        }
    }

    public static void trace(Log log, String message, Object... args) {
        if (log.isTraceEnabled()) {
            log.trace(String.format(message, args));
        }
    }

    public static void warn(Log log, String message) {
        if (log.isWarnEnabled()) {
            log.warn(message);
        }
    }

    public static void warn(Log log, String message, Object... args) {
        if (log.isWarnEnabled()) {
            log.warn(String.format(message, args));
        }
    }

    public static void warn(Log log, String message, Throwable e) {
        if (log.isWarnEnabled()) {
            log.warn(message, e);
        }
    }
}
