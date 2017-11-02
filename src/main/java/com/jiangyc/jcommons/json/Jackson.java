package com.jiangyc.jcommons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiangyc.jcommons.json.spi.JsonProvider;

import java.io.IOException;
import java.io.Reader;

public class Jackson extends JsonProvider {
    // Jackson对象映射
    private ObjectMapper mapper;

    /**
     * 通过默认的<code>ObjectMapper</code>初始化
     */
    public Jackson() {
        this(null);
    }

    /**
     * 通过给定的<code>ObjectMapper</code>初始化
     * @param mapper 初始化需要的<code>ObjectMapper</code>，如果为空，则使用默认的构造方法创建之。
     */
    public Jackson(ObjectMapper mapper) {
        super(mapper);
        this.mapper = (mapper == null) ? new ObjectMapper() : mapper;
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
            return mapper.readValue(src, c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析指定的Json字符串
     * @param src 要作为Json源的字符串
     * @param c 期望的JSon类型，常见的为List.class、Map.class，JsonNode.class等
     * @param <T> 经过Jackson解析后的Json数据
     * @return
     */
    public <T> T fromJson(String src, Class<T> c) {
        try {
            return mapper.readValue(src, c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean enabled() {
        try {
            Class.forName("com.fasterxml.jackson.databind.ObjectMapper");

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public String getId() {
        return "jackson";
    }

    /**
     * 将给定的对象转化为Json
     * @param o 要转化成Json的对象
     * @return 生成的Json对象
     */
    public String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
