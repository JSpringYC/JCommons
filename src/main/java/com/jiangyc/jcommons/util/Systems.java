package com.jiangyc.jcommons.util;

public class Systems {

    // ********************
    //  Operating System
    // ********************
    /**
     * 系统名称
     */
    public static final String OS_NAME = System.getProperty("os.name");
    /**
     * 系统版本
     */
    public static final String OS_VERSION = System.getProperty("os.version");
    /**
     * 系统架构
     */
    public static final String OS_ARCH = System.getProperty("os.arch");
    /**
     * 是否为Windows系统
     */
    public static final boolean IS_OS_WINDOWS = OS_NAME.toLowerCase().contains("windows");
    public static final boolean IS_OS_LINUX = OS_NAME.toLowerCase().contains("linux");

    // ********************
    //  User
    // ********************
    public static final String USER_NAME = System.getProperty("user.name");
    public static final String USER_HOME = System.getProperty("user.home");
    public static final String USER_COUNTRY = System.getProperty("user.country");
    public static final String USER_LANGUAGE = System.getProperty("user.language");
    public static final String USER_TIMEZONE = System.getProperty("user.timezone");

    // ********************
    //  Java
    // ********************
    public static final String JAVA_VERSION = System.getProperty("java.version");

}
