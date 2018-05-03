package com.jiangyc.jcommons.compress.zip;

import com.jiangyc.jcommons.compress.Configure;
import com.jiangyc.jcommons.compress.Encoder;
import com.jiangyc.jcommons.io.Streams;
import com.jiangyc.jcommons.util.Asserts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipEncoder implements Encoder {
    /**
     * 压缩配置
     */
    private ZipConfigure configure = new ZipConfigure();

    @Override
    public Configure getConfigure() {
        return configure;
    }

    @Override
    public void encode(File input, File output) throws IOException {
        Asserts.fileExist(input, "要压缩的文件为空或不存在");
        Asserts.notNull(output, "Output file can not be null!");

        ZipOutputStream zos = null;

        try {
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(output), new CRC32());
            // 设置配置
            zos = new ZipOutputStream(cos, configure.getCharset());
            zos.setLevel(configure.getLevel());
            zos.setComment(configure.getComment());
            zos.setMethod(configure.getMethod());
            // 设置配置
            String basePath = "/";
            encode(input, zos, basePath);
        } finally {
            if (zos != null) {
                zos.close();
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

        ZipOutputStream zos = null;

        try {
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(output), new CRC32());
            // 设置配置
            zos = new ZipOutputStream(cos, configure.getCharset());
            zos.setLevel(configure.getLevel());
            zos.setComment(configure.getComment());
            zos.setMethod(configure.getMethod());
            // 设置配置
            String basePath = "/";
            for (File file : input) {
                encode(file, zos, basePath);
            }
        } finally {
            if (zos != null) {
                zos.close();
            }
        }
    }

    /**
     * 压缩文件或目录
     * @param input 要压缩的文件或文件夹
     * @param zos 压缩输出流
     * @param basePath 基本路径
     */
    private void encode(File input, ZipOutputStream zos, String basePath) throws IOException {
        Asserts.fileExist(input, "要压缩的文件为空或不存在");

        // 压缩文件
        if (input.isFile()) {
            ZipEntry zipEntry = new ZipEntry(basePath + input.getName());
            zos.putNextEntry(zipEntry);
            zipEntry.setSize(input.length());
            zipEntry.setTime(input.lastModified());
            InputStream is = null;
            try {
                is = Streams.asInputStream(input);
                Streams.copy(is, zos);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
            zos.closeEntry();
            return;
        } else if (input.isDirectory()) {
            File[] files = input.listFiles();
            String newBasePath = basePath + input.getName() + "/";
            // 创建空目录
            if (files.length < 1) {
                ZipEntry zipEntry = new ZipEntry(newBasePath);
                zos.putNextEntry(zipEntry);
                zos.closeEntry();
            }
            // 递归压缩目录
            for (File file : files) {
                encode(file, zos, newBasePath);
            }
        } else {
            throw new IllegalArgumentException("Illegal input file: " + input);
        }
    }
}
