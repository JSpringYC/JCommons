package com.jiangyc.jcommons.compress.jar;

import com.jiangyc.jcommons.compress.Configure;
import com.jiangyc.jcommons.compress.Encoder;
import com.jiangyc.jcommons.io.Streams;
import com.jiangyc.jcommons.util.Asserts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class JarEncoder implements Encoder {
    /**
     * 压缩配置
     */
    private JarConfigure configure = new JarConfigure();
    
    @Override
    public Configure getConfigure() {
        return configure;
    }

    @Override
    public void encode(File input, File output) throws IOException {
        Asserts.fileExist(input, "要解压的文件为空或不存在");
        Asserts.notNull(output, "Output file can not be null!");

        JarOutputStream jos = null;

        try {
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(output), new CRC32());
            // 设置配置
            if (configure.getManifest() == null) {
                jos = new JarOutputStream(cos);
            } else {
                jos = new JarOutputStream(cos, configure.getManifest());
            }
            jos.setLevel(configure.getLevel());
            jos.setComment(configure.getComment());
            jos.setMethod(configure.getMethod());
            // 设置配置
            String basePath = "/";
            encode(input, jos, basePath);
        } finally {
            if (jos != null) {
                jos.close();
            }
        }
    }

    @Override
    public void encode(List<File> input, File output) throws IOException {
        Asserts.notNull(input, "Input file can not be null!");
        Asserts.notNull(output, "Output file can not be null!");
        if (input.size() <= 0) {
            throw new IllegalArgumentException("Input files must more than one!");
        }

        JarOutputStream jos = null;

        try {
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(output), new CRC32());
            // 设置配置
            if (configure.getManifest() == null) {
                jos = new JarOutputStream(cos);
            } else {
                jos = new JarOutputStream(cos, configure.getManifest());
            }
            jos.setLevel(configure.getLevel());
            jos.setComment(configure.getComment());
            jos.setMethod(configure.getMethod());
            // 设置配置
            String basePath = "/";
            for (File file : input) {
                encode(file, jos, basePath);
            }
        } finally {
            if (jos != null) {
                jos.close();
            }
        }
    }

    /**
     * 压缩文件或目录
     * @param input 要压缩的文件或文件夹
     * @param jos 压缩输出流
     * @param basePath 基本路径
     */
    private void encode(File input, JarOutputStream jos, String basePath) throws IOException {
        Asserts.fileExist(input, "要压缩的文件为空或不存在");

        // 压缩文件
        if (input.isFile()) {
            JarEntry zipEntry = new JarEntry(basePath + input.getName());
            jos.putNextEntry(zipEntry);
            zipEntry.setSize(input.length());
            zipEntry.setTime(input.lastModified());
            InputStream is = null;
            try {
                is = Streams.asInputStream(input);
                Streams.copy(is, jos);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            jos.closeEntry();
            return;
        } else if (input.isDirectory()) {
            File[] files = input.listFiles();
            String newBasePath = basePath + input.getName() + "/";
            // 创建空目录
            if (files.length < 1) {
                JarEntry zipEntry = new JarEntry(newBasePath);
                jos.putNextEntry(zipEntry);
                jos.closeEntry();
            }
            // 递归压缩目录
            for (File file : files) {
                encode(file, jos, newBasePath);
            }
        } else {
            throw new IllegalArgumentException("Illegal input file: " + input);
        }
    }
}
