package com.jiangyc.jcommons.log.jdk;

import com.jiangyc.jcommons.log.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JdkLog implements Log {
    // JDK Log
    private Logger logger;

    public JdkLog(String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public boolean isFatelEnable() {
        return logger.isLoggable(JdkLevel.FATEL);
    }

    @Override
    public void fatel(Object msg) {
        fatel(msg, null);
    }

    @Override
    public void fatel(Object msg, Throwable e) {
        log(JdkLevel.FATEL, msg, e);
    }

    @Override
    public boolean isErrorEnable() {

        return logger.isLoggable(JdkLevel.ERROR);
    }

    @Override
    public void error(Object msg) {
        error(msg, null);
    }

    @Override
    public void error(Object msg, Throwable e) {
        log(JdkLevel.ERROR, msg, e);
    }

    @Override
    public boolean isWarnEnable() {
        return logger.isLoggable(JdkLevel.WARN);
    }

    @Override
    public void warn(Object msg) {
        warn(msg, null);
    }

    @Override
    public void warn(Object msg, Throwable e) {
        log(JdkLevel.WARN, msg, e);
    }

    @Override
    public boolean isInfoEnable() {
        return logger.isLoggable(JdkLevel.INFO);
    }

    @Override
    public void info(Object msg) {
        info(msg, null);
    }

    @Override
    public void info(Object msg, Throwable e) {
        log(JdkLevel.INFO, msg, e);
    }

    @Override
    public boolean isDebugEnable() {
        return logger.isLoggable(JdkLevel.DEBUG);
    }

    @Override
    public void debug(Object msg) {
        debug(msg, null);
    }

    @Override
    public void debug(Object msg, Throwable e) {
        log(JdkLevel.DEBUG, msg, e);
    }

    @Override
    public boolean isTraceEnable() {
        return logger.isLoggable(JdkLevel.TRACE);
    }

    @Override
    public void trace(Object msg) {
        trace(msg, null);
    }

    @Override
    public void trace(Object msg, Throwable e) {
        log(JdkLevel.TRACE, msg, e);
    }

    public void log(Level level, Object msg, Throwable e) {

        msg = (msg == null) ? "null" : msg.toString();

        logger.log(level, msg.toString(), e);
    }
}
