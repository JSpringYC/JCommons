package com.jiangyc.jcommons.log;

import com.jiangyc.jcommons.log.spi.LogBinder;

import java.util.ServiceLoader;

public class LogFactory {
    private static LogBinder DEFAULT_LOG_BINDER;

    static {
        ServiceLoader<LogBinder> serviceLoader = ServiceLoader.load(LogBinder.class);

        for (LogBinder logBinder : serviceLoader) {

            if (!logBinder.enable()) {
                continue;
            }

            if (DEFAULT_LOG_BINDER == null) {
                DEFAULT_LOG_BINDER = logBinder;
            } else if (logBinder.getLevel() > DEFAULT_LOG_BINDER.getLevel()) {
                DEFAULT_LOG_BINDER = logBinder;
            }
        }
    }

    public static Log getLog(Class c) {
        return getLog(c.getName());
    }

    private static Log getLog(String name) {
        return DEFAULT_LOG_BINDER.getLog(name);
    }
}
