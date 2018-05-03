package com.jiangyc.jcommons.compress.zip;

import com.jiangyc.jcommons.compress.Configure;
import com.jiangyc.jcommons.util.Systems;
import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;

@Data
public class ZipConfigure implements Configure {
    /**
     * 压缩文件的文件名编码
     */
    private Charset charset = Systems.IS_OS_WINDOWS ? Charset.forName("GBK") : StandardCharsets.UTF_8;
    /**
     * The ZIP file comment.
     */
    private String comment;
    /**
     * The compression level (0-9) for subsequent entries which are DEFLATED.
     * The default setting is DEFAULT_COMPRESSION.
     *
     * 0: Compression level for no compression.
     * 1: Compression level for fastest compression.
     * 9: Compression level for best compression.
     */
    private int level = 5;
    /**
     * The default compression method for subsequent entries. This
     * default will be used whenever the compression method is not specified
     * for an individual ZIP file entry, and is initially set to DEFLATED.
     */
    private int method = ZipEntry.DEFLATED;
}
