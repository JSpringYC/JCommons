package com.jiangyc.jcommons.json;

import com.alibaba.fastjson.JSONObject;
import com.jiangyc.jcommons.json.spi.JsonProvider;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Fastjson extends JsonProvider {

    /**
     * 用默认的配置对象初始化
     */
    public Fastjson() {
        this(null);
    }

    /**
     * 用给定的配置对象初始化
     *
     * @param config 配置信息，如Jackson的ObjectMapper
     */
    public Fastjson(Object config) {
        super(config);
    }

    /**
     * 从给定的字符流中读取数据，并将之作为Json解析。
     * @param src 要作为Json源的字符流
     * @param c 期望的JSon类型，常见的为List.class、Map.class，JsonNode.class等
     * @param <T> 经过Jackson解析后的Json数据
     * @return
     */
    public <T> T fromJson(Reader src, Class<T> c) {
        try {
            String s = FileCopyUtils.copyToString(src);
            return JSONObject.parseObject(s, c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析指定的Json字符串
     * @param src 要作为Json源的字符串
     * @param c 期望的JSon类型，常见的为List.class、Map.class
     * @param <T> 经过Jackson解析后的Json数据
     * @return
     */
    public <T> T fromJson(String src, Class<T> c) {
        return JSONObject.parseObject(src, c);
    }

    @Override
    public boolean enabled() {
        try {
            Class.forName("com.alibaba.fastjson.JSONObject");

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public String getId() {
        return "fastjson";
    }

    /**
     * 将给定的对象转化为Json
     * @param o 要转化成Json的对象
     * @return 生成的Json对象
     */
    public String toJson(Object o) {
        return JSONObject.toJSONString(o);
    }

    /**
     * 将给定的对象转化为Json，并将之写入到指定的字符流中
     * @param o 要转化成Json的对象
     * @param writer 要写入的字符流
     */
    public void toJson(Object o, Writer writer) {
        try {
            String s = toJson(o);
            FileCopyUtils.copy(s, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
