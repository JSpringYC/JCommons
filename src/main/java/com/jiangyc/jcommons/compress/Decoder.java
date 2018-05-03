package com.jiangyc.jcommons.compress;

import java.io.File;
import java.io.IOException;

/**
 * 压缩解码器
 */
public interface Decoder {

    /**
     * 压缩配置
     * @return
     */
    Configure getConfigure();

    /**
     * 压缩解码器
     * @param input 压缩文件
     * @param output 要解压到的目录
     */
    void decode(File input, File output) throws IOException;
}
