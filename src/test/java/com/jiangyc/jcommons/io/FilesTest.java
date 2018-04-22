package com.jiangyc.jcommons.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FilesTest {

    @Test
    public void testCreateFile() throws IOException {
        File file = new File("C:\\Users\\jiangyc\\Desktop\\test\\test.txt");

        File file2 = Files.requireFile(file);

        System.out.println(file2);
    }
}
