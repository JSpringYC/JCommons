package com.jiangyc.jcommons.log.spi;

import com.jiangyc.jcommons.log.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractLogBinder implements LogBinder {
    // log 缓存
    protected Map<String, Log> logMap = new ConcurrentHashMap<>();

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return "com.jiangyc.jlog.Log";
    }

    /**
     * 基本的实现，加入了Map缓存功能
     * @param name log名称
     * @return 日志
     */
    public synchronized Log getLog(String name) {
        Log log = logMap.get(name);
        // log不存在，则创建，并加入Map
        if (log == null) {
            log = createLog(name);
            logMap.put(name, log);
        }

        return log;
    }

    /**
     * 创建一个Log实现
     * @param name 日志名称
     * @return 日志
     */
    public abstract Log createLog(String name);
}
