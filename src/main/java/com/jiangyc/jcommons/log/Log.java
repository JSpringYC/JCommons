package com.jiangyc.jcommons.log;

public interface Log {

    boolean isFatelEnable();

    void fatel(Object msg);

    void fatel(Object msg, Throwable e);

    boolean isErrorEnable();

    void error(Object msg);

    void error(Object msg, Throwable e);

    boolean isWarnEnable();

    void warn(Object msg);

    void warn(Object msg, Throwable e);

    boolean isInfoEnable();

    void info(Object msg);

    void info(Object msg, Throwable e);

    boolean isDebugEnable();

    void debug(Object msg);

    void debug(Object msg, Throwable e);

    boolean isTraceEnable();

    void trace(Object msg);

    void trace(Object msg, Throwable e);
}
