package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;

import java.io.File;
import java.io.IOException;

/**
 * 文件操作工具类，提供了一系列文件和流操作的静态方法。
 */
public class Files {
    // 默认的缓存大小
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    // ********************
    //  文件操作
    // ********************

    /**
     * 创新新文件，当文件的父目录不存在时，根据参数决定是否创建父目录
     * @param file 要新建的文件
     * @param createParentFile 是否创建父目录
     * @return
     */
    public static boolean newFile(File file, boolean createParentFile) {
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
