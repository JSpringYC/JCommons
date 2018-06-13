package com.jiangyc.jcommons.compress.jar;

import com.jiangyc.jcommons.compress.zip.ZipConfigure;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.jar.Manifest;

@Data
@EqualsAndHashCode(callSuper = true)
public class JarConfigure extends ZipConfigure {

    private Manifest manifest;
}
