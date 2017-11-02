package com.jiangyc.jcommons.json.spi;


import java.io.Reader;

public abstract class JsonProvider {
    // Json提供商的特定配置
    protected Object config;

    /**
     * 用给定的配置对象初始化
     * @param config 配置信息，如Jackson的ObjectMapper
     */
    public JsonProvider(Object config) {
        this.config = config;
    }

    /**
     * 从给定的字符流中读取数据，并将之作为Json解析。
     * @param src 要作为Json源的字符流
     * @param c 期望的JSon类型，常见的为List.class、Map.class，JsonNode.class等
     * @param <T> 经过解析后的Json数据
     * @return
     */
    public abstract  <T> T fromJson(Reader src, Class<T> c);

    /**
     * 解析指定的Json字符串
     * @param src 要作为Json源的字符串
     * @param c 期望的JSon类型，常见的为List.class、Map.class
     * @param <T> 经过解析后的Json数据
     * @return
     */
    public abstract  <T> T fromJson(String src, Class<T> c);

    /**
     * 检查此服务是否处于可用状态
     * @return 此服务是否处于可用状态
     */
    public abstract boolean enabled();

    /**
     * 获取此服务的ID
     * @return
     */
    public abstract String getId();

    /**
     * 将给定的对象转化为Json
     * @param o 要转化成Json的对象
     * @return 生成的Json对象
     */
    public abstract String toJson(Object o);
}
