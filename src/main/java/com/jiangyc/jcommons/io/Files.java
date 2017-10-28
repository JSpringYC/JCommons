package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;

import java.io.*;
import java.net.URI;

/**
 * 文件操作工具类，提供了一系列文件操作的静态方法。
 */
public class Files {

    // ********************
    //  文件操作
    // ********************

    /**
     * 删除文件或文件夹
     * @param file 要删除的文件
     * @param r 是否对文件夹进行递归操作
     * @return 是否成功删除该文件
     */
    public static boolean delete(File file, boolean r) {
        Asserts.fileExist(file, null);

        // 删除文件
        if (file.isFile()) {
            return file.delete();
        }

        File[] subFiles = file.listFiles();

        // 递归删除
        if (subFiles != null && subFiles.length > 0) {
            if (!r) {
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

    // ********************
    //  流操作
    // ********************
    public static InputStream asInputStream(Object input) throws IOException {
        Asserts.notNull(input);

        if (input instanceof BufferedInputStream) {
            return (BufferedInputStream) input;
        } else if (input instanceof InputStream) {
            return new BufferedInputStream((InputStream) input);
        } else if (input instanceof File) {
            return new BufferedInputStream(new FileInputStream((File) input));
        } else if (input instanceof URI) {
            return new BufferedInputStream(new FileInputStream(new File((URI)input)));
        } else {
            return new BufferedInputStream(new FileInputStream(input.toString()));
        }
    }

    public static OutputStream asOutputStream(Object output) throws IOException {
        Asserts.notNull(output);

        if (output instanceof BufferedOutputStream) {
            return (BufferedOutputStream) output;
        } else if (output instanceof OutputStream) {
            return new BufferedOutputStream((OutputStream) output);
        } else if (output instanceof File) {
            return new BufferedOutputStream(new FileOutputStream((File) output));
        } else if (output instanceof URI) {
            return new BufferedOutputStream(new FileOutputStream(new File((URI)output)));
        } else {
            return new BufferedOutputStream(new FileOutputStream(output.toString()));
        }
    }

    public static Reader asReader(Object input, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(asInputStream(input), charset));
    }

    public static Writer asWriter(Object output, String charset) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(asOutputStream(output), charset));
    }
}
