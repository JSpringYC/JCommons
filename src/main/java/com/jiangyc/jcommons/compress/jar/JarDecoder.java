package com.jiangyc.jcommons.compress.jar;

import com.jiangyc.jcommons.compress.Configure;
import com.jiangyc.jcommons.compress.Decoder;

import java.io.File;
import java.io.IOException;

public class JarDecoder implements Decoder {

    @Override
    public Configure getConfigure() {
        return null;
    }

    @Override
    public void decode(File input, File output) throws IOException {

    }
}
