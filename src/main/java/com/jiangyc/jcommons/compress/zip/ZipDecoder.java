package com.jiangyc.jcommons.compress.zip;

import com.jiangyc.jcommons.compress.Configure;
import com.jiangyc.jcommons.compress.Decoder;
import com.jiangyc.jcommons.io.Streams;
import com.jiangyc.jcommons.util.Asserts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDecoder implements Decoder {
    /**
     * 压缩配置
     */
    private ZipConfigure configure = new ZipConfigure();

    @Override
    public Configure getConfigure() {
        return configure;
    }

    @Override
    public void decode(File input, File output) throws IOException {
        Asserts.fileExist(input, "要解压的文件为空或不存在");
        Asserts.notNull(output, "Output file can not be null!");
        if (!input.isFile()) {
            throw new IllegalArgumentException("Illegal input file: " + input);
        }

        ZipInputStream zis = null;

        try {
            CheckedInputStream cis = new CheckedInputStream(new FileInputStream(input), new CRC32());
            // 设置配置
            zis = new ZipInputStream(cis, configure.getCharset());
            // 设置配置
            String basePath = "/";
            decode(zis, output);
        } finally {
            if (zis != null) {
                zis.close();
            }
        }
    }

    /**
     * 解压文件或目录
     * @param zis 压缩输入流
     * @param output 要解压到的目录
     */
    private void decode(ZipInputStream zis, File output) throws IOException {
        ZipEntry zipEntry = null;

        while ((zipEntry = zis.getNextEntry()) != null) {
            File destFile = new File(output.getPath() + "/" + zipEntry.getName());

            destFile.getParentFile().mkdirs();

            if (zipEntry.isDirectory()) {
                destFile.mkdirs();
            } else {
                OutputStream os = null;
                try {
                    os = Streams.asOutputStream(destFile);
                    Streams.copy(zis, os);
                } finally {
                    if (os != null) {
                        os.close();
                    }
                }
            }

            zis.closeEntry();
        }
    }
}
