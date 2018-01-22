package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作工具类，提供了一系列文件和流操作的静态方法。
 */
public class Files {
    // 默认的缓存大小
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    // ********************
    //  判断操作
    // ********************

    /**
     * 确保给定的文件存在。当不存在时，将会抛出<code>IllegalArgumentException</code>异常
     * @param file
     * @return
     */
    public static File requireExists(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("file must be exists: " + file);
        }
        return file;
    }

    /**
     * 确保给定的文件存在且类型为文件，否则将会抛出<code>IllegalArgumentException</code>异常
     * @param file
     * @return
     */
    public static File requireFile(File file) {
        requireExists(file);

        if (!file.isFile()) {
            throw new IllegalArgumentException("the file must be an exist file: " + file);
        }

        return file;
    }

    /**
     * 确保给定的文件存在且类型为目录，否则将会抛出<code>IllegalArgumentException</code>异常
     * @param file
     * @return
     */
    public static File requireDirectory(File file) {
        requireExists(file);

        if (!file.isDirectory()) {
            throw new IllegalArgumentException("the file must be an exist directory: " + file);
        }

        return file;
    }

    // ********************
    //  读写操作
    // ********************

    /**
     * 以给定的文件作为源来创建一个输入流.
     * @param file
     * @return
     * @throws IOException
     */
    public static InputStream asInputStream(File file) throws IOException {
        requireFile(file);
        return Streams.asInputStream(file);
    }

    /**
     * 以给定的文件作为源来创建一个输出流.
     * @param file
     * @return
     * @throws IOException
     */
    public static OutputStream asOutputStream(File file) throws IOException {
        requireFile(file);
        return Streams.asOutputStream(file);
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
