package com.jiangyc.jcommons.log.log4j;

import com.jiangyc.jcommons.log.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLog implements Log {
    // log4j log
    private Logger logger;

    public Log4jLog(String name) {
        logger = Logger.getLogger(name);
    }

    @Override
    public boolean isFatelEnable() {
        return logger.isEnabledFor(Level.FATAL);
    }

    @Override
    public void fatel(Object msg) {
        fatel(msg, null);
    }

    @Override
    public void fatel(Object msg, Throwable e) {
        logger.fatal(msg, e);
    }

    @Override
    public boolean isErrorEnable() {
        return logger.isEnabledFor(Level.ERROR);
    }

    @Override
    public void error(Object msg) {
        error(msg, null);
    }

    @Override
    public void error(Object msg, Throwable e) {
        logger.error(msg, e);
    }

    @Override
    public boolean isWarnEnable() {
        return logger.isEnabledFor(Level.WARN);
    }

    @Override
    public void warn(Object msg) {
        warn(msg, null);
    }

    @Override
    public void warn(Object msg, Throwable e) {
        logger.warn(msg, e);
    }

    @Override
    public boolean isInfoEnable() {
        return logger.isEnabledFor(Level.INFO);
    }

    @Override
    public void info(Object msg) {
        info(msg, null);
    }

    @Override
    public void info(Object msg, Throwable e) {
        logger.info(msg, e);
    }

    @Override
    public boolean isDebugEnable() {
        return logger.isEnabledFor(Level.DEBUG);
    }

    @Override
    public void debug(Object msg) {
        debug(msg, null);
    }

    @Override
    public void debug(Object msg, Throwable e) {
        logger.debug(msg, e);
    }

    @Override
    public boolean isTraceEnable() {
        return logger.isEnabledFor(Level.TRACE);
    }

    @Override
    public void trace(Object msg) {
        trace(msg, null);
    }

    @Override
    public void trace(Object msg, Throwable e) {
        logger.trace(msg, e);
    }
}
