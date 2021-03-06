package com.jiangyc.jcommons.compress;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 压缩编码器
 */
public interface Encoder {

    /**
     * 压缩配置
     * @return 获取配置信息
     */
    Configure getConfigure();

    /**
     * 压缩编码器
     * @param input 要压缩的源文件
     * @param output 压缩文件
     * @throws IOException 当发生IO异常时抛出
     */
    void encode(File input, File output) throws IOException;

    /**
     * 压缩多个文件
     * @param input 要压缩的源文件集合
     * @param output 压缩文件
     * @throws IOException 当发生IO异常时抛出
     */
    void encode(List<File> input, File output) throws IOException;
}
