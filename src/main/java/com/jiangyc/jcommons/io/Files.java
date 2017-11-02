package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
    //  流操作 - 包装
    // ********************

    /**
     * 将给定的对象包装成一个带有缓存功能的字节输入流。
     * @param input 要包装成字节流的对象，可以为InputStream、File、URI、和字符串形式的文件路径。
     * @return 一个带有缓存功能的字节流，通常为<code>BufferedInputStream</code>
     * @throws IOException 当发生IO异常时，抛出此异常
     */
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

    /**
     * 将给定的对象包装成一个带有缓存功能的字节流。
     * @param output 要包装成字节流的对象，可以为OutputStream、File、URI、和字符串形式的文件路径。
     * @return 一个带有缓存功能的字节流，通常为<code>BufferedOutputStream</code>
     * @throws IOException 当发生IO异常时，抛出此异常
     */
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

    /**
     * 将给定的对象包装成一个带有缓存功能的字符流。
     * @param input 要包装成字符流的对象，可以为Reader、InputStream、File、URI、和字符串形式的文件路径。
     * @return 一个带有缓存功能的字符流，通常为<code>BufferedReader</code>
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static Reader asReader(Object input, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(asInputStream(input), charset));
    }

    /**
     * 将给定的对象包装成一个带有缓存功能的字符流。
     * @param output 要包装成字符流的对象，可以为Writer、OutputStream、File、URI、和字符串形式的文件路径。
     * @return 一个带有缓存功能的字符流，通常为<code>BufferedWriter</code>
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static Writer asWriter(Object output, String charset) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(asOutputStream(output), charset));
    }

    // ********************
    //  流操作 - 读取
    // ********************

    /**
     * 读取数据，并将之转化为字符串。
     * @param input 数据源
     * @return 读取的字符串
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static String toString(Object input) throws IOException {
        return toString(input, null);
    }

    /**
     * 读取数据，并将之转化为字符串。
     * @param input 数据源
     * @param charset 编码
     * @return 读取的字符串
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static String toString(Object input, String charset) throws IOException {
        // 数据源
        Reader reader = asReader(input, charset);
        // 用来保存读取的字符串
        StringWriter writer = new StringWriter();
        // 数据复制
        copy(reader, writer);

        return writer.toString();
    }

    /**
     * 读取数据，并将之转化为字符串数组。
     * @param input 数据源
     * @param charset 编码
     * @return 读取的字符串
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static String[] toStringArray(Object input, String charset) throws IOException {
        BufferedReader bufferedReader = (BufferedReader) asReader(input, charset);
        String line  = null;

        List<String> strings = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            strings.add(line);
        }

        String[] stringArray = new String[strings.size()];
        return strings.toArray(stringArray);
    }

    /**
     * 读取数据，并将之转化为字节数据。
     * @param input 数据源
     * @return 读取的字节数据
     */
    public static byte[] toByteArray(Object input) throws IOException {
        BufferedInputStream bufis = (BufferedInputStream) asInputStream(input);
        // 用于存储读取的字节
        ByteOutputStream bos = new ByteOutputStream(DEFAULT_BUFFER_SIZE);

        copyAndClose(bufis, bos);

        return bos.getBytes();
    }

    // ********************
    //  流操作 - 读写
    // ********************

    /**
     * 字符流复制，此方法不会自动关闭对应的流，以便流的下载调用。如要关闭，请用copyAndClose方法
     * @param input 字符输入流
     * @param output 字符输出流
     * @throws IOException 当发生IO异常时，抛出此异常
     * @see #copyAndClose(Reader, Writer)
     */
    public static void copy(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        int len = -1;

        while ((len = input.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }

        output.flush();
    }

    /**
     * 字符流复制，并在复制完成后关闭对应的流
     * @param input 字符输入流
     * @param output 字符输出流
     * @throws IOException 当发生IO异常时，抛出此异常
     * @see #copy(Reader, Writer)
     */
    public static void copyAndClose(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        int len = -1;

        while ((len = input.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }

        output.flush();
        Closeables.closeQuietly(input, output);
    }

    /**
     * 字节流复制，此方法不会自动关闭对应的流，以便流的下载调用。如要关闭，请用copyAndClose方法
     * @param input 字节输入流
     * @param output 字节输出流
     * @throws IOException 当发生IO异常时，抛出此异常
     * @see #copyAndClose(InputStream, OutputStream)
     */
    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int len = -1;

        while ((len = input.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }

        output.flush();
    }

    /**
     * 字节流复制，并在复制完成后关闭对应的流
     * @param input 字节输入流
     * @param output 字节输出流
     * @throws IOException 当发生IO异常时，抛出此异常
     * @see #copy(InputStream, OutputStream)
     */
    public static void copyAndClose(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int len = -1;

        while ((len = input.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }

        output.flush();
        Closeables.closeQuietly(input, output);
    }

    // ********************
    //  流操作 - 写出
    // ********************

    /**
     * 将给定的字符串出到输出源中
     * @param s 要写出的字符串
     * @param output 输出源，可以为文件、字节流、字符流等
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(String s, Object output) throws IOException {
        writer(s, output, null);
    }

    /**
     * 将给定的字符串出到输出源中
     * @param s 要写出的字符串
     * @param output 输出源，可以为文件、字节流、字符流等
     * @param charset 创建流所使用的字符编码
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(String s, Object output, String charset) throws IOException {
        Writer writer = asWriter(output, charset);

        writer.write(s);
    }

    /**
     * 将给定的字符数组写出到输出源中
     * @param cs 要写出的字符数组
     * @param output 输出源，可以为文件、字节流、字符流等
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(char[] cs, Object output) throws IOException {
        writer(cs, 0, cs.length, output, null);
    }

    /**
     * 将给定的字符数组写出到输出源中
     * @param cs 要写出的字符数组
     * @param off 字符数据的起始下标
     * @param len 长度
     * @param output 输出源，可以为文件、字节流、字符流等
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(char[] cs, int off, int len, Object output) throws IOException {
        writer(cs, off, len, output, null);
    }

    /**
     * 将给定的字符数组写出到输出源中
     * @param buf 要写出的字符数组
     * @param off 字符数据的起始下标
     * @param len 长度
     * @param output 输出源，可以为文件、字节流、字符流等
     * @param charset 创建流所使用的字符编码
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(char[] buf, int off, int len, Object output, String charset) throws IOException {
        Writer writer = asWriter(output, charset);

        writer.write(buf, off, len);
    }

    /**
     * 将给定的字符串数组写出到输出源中
     * @param ss 要写出的字符串数组
     * @param output 输出源，可以为文件、字节流、字符流等
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(String[] ss, Object output) throws IOException {
        writer(ss, output, null);
    }

    /**
     * 将给定的字符数组写出到输出源中
     * @param ss 要写出的字符串数组
     * @param output 输出源，可以为文件、字节流、字符流等
     * @param charset 创建流所使用的字符编码
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void writer(String[] ss, Object output, String charset) throws IOException {
        for (String s : ss) {
            writer(s, output, charset);
        }
    }

    /**
     * 将给定的字节数组写出到输出源中
     * @param buf 要写出的字节数组
     * @param output 输出源，可以为文件、字节流
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void stream(byte[] buf, Object output) throws IOException {
        stream(buf, 0, buf.length, output);
    }

    /**
     *
     * @param buf 要写出的字节数组
     * @param off 字节数组的起始位置
     * @param len 字节数组的数据长度
     * @param output 输出源，可以为文件、字节流
     * @throws IOException 当发生IO异常时，抛出此异常
     */
    public static void stream(byte[] buf, int off, int len, Object output) throws IOException {
        OutputStream os = asOutputStream(output);

        os.write(buf, off, len);
    }
}
