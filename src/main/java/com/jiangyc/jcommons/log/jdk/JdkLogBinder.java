package com.jiangyc.jcommons.log.jdk;

import com.jiangyc.jcommons.log.Log;
import com.jiangyc.jcommons.log.spi.AbstractLogBinder;

public class JdkLogBinder extends AbstractLogBinder {

    @Override
    public Log createLog(String name) {
        return new JdkLog(name);
    }

    @Override
    public int getLevel() {
        return 100;
    }

    @Override
    public String getName() {
        return "com.jiangyc.jlog.jdk.JdkLog";
    }

    @Override
    public boolean enable() {
        try {
            Class.forName("java.util.logging.Logger");
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }
}
