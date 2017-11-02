package com.jiangyc.jcommons.log.log4j;

import com.jiangyc.jcommons.log.Log;
import com.jiangyc.jcommons.log.spi.AbstractLogBinder;

public class Log4jBinder extends AbstractLogBinder {

    @Override
    public int getLevel() {
        return 200;
    }

    @Override
    public String getName() {
        return "com.jiangyc.jlog.log4j.Log4jLog";
    }

    @Override
    public Log createLog(String name) {
        return new Log4jLog(name);
    }

    @Override
    public boolean enable() {
        try {
            Class.forName("org.apache.log4j.Logger");
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }
}
