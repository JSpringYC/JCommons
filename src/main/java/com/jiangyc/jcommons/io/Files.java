package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;

import java.io.*;

/**
 * 文件操作工具类，提供了一系列文件判断和读写操作的静态方法。
 */
public class Files {
    /**
     * 默认的缓存大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    // ********************
    //  判断操作
    // ********************

    /**
     * 判断给定的文件是否存在。如果存在则返回<code>true</code>，否则返回<code>false</code>.
     * @param file 要判断的文件
     * @return 给定的文件是否存在
     */
    public static boolean isFile(File file) {
        return (file != null && file.isFile()) ? true : false;
    }

    /**
     * 判断给定的目录是否存在。如果存在则返回<code>true</code>，否则返回<code>false</code>.
     * @param file 要判断的目录
     * @return 给定的目录是否存在
     */
    public static boolean isDirectory(File file) {
        return (file != null && file.isDirectory()) ? true : false;
    }

    /**
     * 判断给定的文件是否存在。如果存在则返回之，否则尝试创建并返回，若创建失败则抛出<code>RuntimeException</code>异常.
     * @param file 要判断的文件
     * @param createParentFile 是否创建父目录
     * @return 存在的文件
     */
    public static File requireFile(File file, boolean createParentFile) {
        Asserts.notNull(file, "the file must not be null!");

        if (!file.exists()) {
            if (createNewFile(file, createParentFile)) {
                return file;
            }
        }

        if (!file.isFile()) {
            throw new RuntimeException("file already exists and is not a file: " + file);
        }

        return file;
    }

    /**
     * 判断给定的目录是否存在。如果存在则返回之，否则尝试创建并返回，若创建失败则抛出<code>RuntimeException</code>异常.
     * @param dir 要判断的文件
     * @return 存在的目录
     */
    public static File requireDirectory(File dir) {
        Asserts.notNull(dir, "the file must not be null!");

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("can not create directory: " + dir);
            }
        }

        if (!dir.isDirectory()) {
            throw new RuntimeException("file already exists and is not a directory: " + dir);
        }

        return dir;
    }

    // ********************
    //  文件操作
    // ********************

    /**
     * 创新新文件，当文件的父目录不存在时，根据参数决定是否创建父目录
     * @param file 要新建的文件
     * @param createParentFile 是否创建父目录
     * @return
     */
    public static boolean createNewFile(File file, boolean createParentFile) {
        Asserts.notNull("文件不能为空!");

        if (file.exists()) {
            return false;
        }

        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            if (createParentFile) {
                parent.mkdirs();
            }
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 创新新文件，当文件的父目录不存在时，根据参数决定是否创建父目录。当文件存在时，设置文件的最后修改时间.
     * @param file 要新建的文件
     * @param createParentFile 是否创建父目录
     * @return
     */
    public static boolean touch(File file, boolean createParentFile) {
        Asserts.notNull("文件不能为空!");
        // 当文件存在时，直接设置最后修改时间
        if (file.exists()) {
            return file.setLastModified(System.currentTimeMillis());
        }
        // 不存在时，创建文件
        return createNewFile(file, createParentFile);
    }

    /**
     * 删除文件或文件夹
     * @param file 要删除的文件
     * @param recursion 是否对文件夹进行递归操作
     * @return 是否成功删除该文件
     */
    public static boolean delete(File file, boolean recursion) {
        Asserts.fileExist(file, "要删除的文件不存在！");

        // 删除文件
        if (file.isFile()) {
            return file.delete();
        }

        File[] subFiles = file.listFiles();

        // 递归删除
        if (subFiles != null && subFiles.length > 0) {
            if (!recursion) {
                return false;
            }
            // 递归删除子文件
            for (File subFile : file.listFiles()) {
                if (!delete(file, true)) {
                    return false;
                }
            }
        }
        // 最后删除文件
        return file.delete();
    }
}
