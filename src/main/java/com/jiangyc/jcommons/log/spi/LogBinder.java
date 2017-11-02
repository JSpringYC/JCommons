package com.jiangyc.jcommons.log.spi;

import com.jiangyc.jcommons.log.Log;

/**
 * Log绑定，各个Log实现需实现此类，以便LogFactory能动态加载该实现
 * @see com.jiangyc.jcommons.log.LogFactory
 * @see java.util.ServiceLoader
 */
public interface LogBinder {

    String getName();

    int getLevel();

    boolean enable();

    public Log getLog(String name);
}
