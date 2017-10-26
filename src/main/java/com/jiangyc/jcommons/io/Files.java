package com.jiangyc.jcommons.io;

import com.jiangyc.jcommons.util.Asserts;

import java.io.*;

import java.net.URI;

public class Files {

    public static InputStream asInputStream(Object input) throws IOException {
        Asserts.notNull(input);

        if (input instanceof BufferedInputStream) {
            return (BufferedInputStream) input;
        } else if (input instanceof InputStream) {
            return new BufferedInputStream((InputStream) input);
        } else if (input instanceof File) {
            return new BufferedInputStream(new FileInputStream((File) input));
        } else if (input instanceof URI) {
            return new BufferedInputStream(new FileInputStream(new File((URI)input)));
        } else {
            return new BufferedInputStream(new FileInputStream(input.toString()));
        }
    }

    public static OutputStream asOutputStream(Object output) throws IOException {
        Asserts.notNull(output);

        if (output instanceof BufferedOutputStream) {
            return (BufferedOutputStream) output;
        } else if (output instanceof OutputStream) {
            return new BufferedOutputStream((OutputStream) output);
        } else if (output instanceof File) {
            return new BufferedOutputStream(new FileOutputStream((File) output));
        } else if (output instanceof URI) {
            return new BufferedOutputStream(new FileOutputStream(new File((URI)output)));
        } else {
            return new BufferedOutputStream(new FileOutputStream(output.toString()));
        }
    }

    public static Reader asReader(Object input, String charset) throws IOException {
        return new BufferedReader(new InputStreamReader(asInputStream(input), charset));
    }

    public static Writer asWriter(Object output, String charset) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(asOutputStream(output), charset));
    }
}
