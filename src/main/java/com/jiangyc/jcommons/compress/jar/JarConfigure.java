package com.jiangyc.jcommons.compress.jar;

import com.jiangyc.jcommons.compress.zip.ZipConfigure;
import lombok.Data;

import java.util.jar.Manifest;

@Data
public class JarConfigure extends ZipConfigure {

    private Manifest manifest;
}
