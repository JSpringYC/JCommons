package com.jiangyc.jcommons.compress;

import java.io.File;
import java.io.IOException;

/**
 * 压缩解码器
 */
public interface Decoder {

    /**
     * 压缩配置
     * @return 获取默认配置
     */
    Configure getConfigure();

    /**
     * 压缩解码器
     * @param input 压缩文件
     * @param output 要解压到的目录
     * @throws IOException 当发生IO异常时抛出
     */
    void decode(File input, File output) throws IOException;
}
