package com.jiangyc.jcommons.log.jdk;

import java.util.logging.Level;

public class JdkLevel extends Level {

    public JdkLevel(String name, int value) {
        super(name, value);
    }

    public static final Level FATEL = new JdkLevel("FATEL", 1000);;

    public static final Level ERROR = new JdkLevel("ERROR", 950);

    public static final Level WARN = new JdkLevel("WARN", 900);;

    public static final Level DEBUG = new JdkLevel("DEBUG", 700);

    public static final Level TRACE = new JdkLevel("TRACE", 500);
}
