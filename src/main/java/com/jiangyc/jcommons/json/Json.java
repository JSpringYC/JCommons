package com.jiangyc.jcommons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jiangyc.jcommons.json.spi.JsonProvider;

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class Json {
    // 存储已实现的Json服务
    private static Map<String, JsonProvider> jsonProviderMap;
    // 默认的Json服务
    private static JsonProvider provider;

    static {
        ServiceLoader<JsonProvider> jsonProviders = ServiceLoader.load(JsonProvider.class);

        if (jsonProviders != null) {
            jsonProviderMap = new LinkedHashMap<>();
        }

        for (JsonProvider jsonProvider : jsonProviders) {
            if (jsonProvider.enabled()) {
                jsonProviderMap.put(jsonProvider.getId().toLowerCase(), jsonProvider);

                if (provider == null) {
                    provider = jsonProvider;
                }
            }
        }
    }

    public static JsonProvider getJsonProvider() {
        if (jsonProviderMap.containsKey("fastjson")) {
            return jsonProviderMap.get("fastjson");
        } else if (jsonProviderMap.containsKey("jackson")) {
            return jsonProviderMap.get("jackson");
        }

        return null;
    }

    public static JsonProvider getJsonProvider(String id) {
        return jsonProviderMap.get(id);
    }

    /**
     * 从给定的字符流中读取数据，并将之作为Json解析。
     * @param src 要作为Json源的字符流
     * @param c 期望的JSon类型，常见的为List.class、Map.class，JsonNode.class等
     * @param <T> 经过Jackson解析后的Json数据
     * @return
     */
    public static <T> T fromJson(Reader src, Class<T> c) {
        return provider.fromJson(src, c);
    }

    /**
     * 解析指定的Json字符串
     * @param src 要作为Json源的字符串
     * @param c 期望的JSon类型，常见的为List.class、Map.class，JsonNode.class等
     * @param <T> 经过Jackson解析后的Json数据
     * @return
     */
    public <T> T fromJson(String src, Class<T> c) {
        return provider.fromJson(src, c);
    }

    /**
     * 将给定的对象转化为Json
     * @param o 要转化成Json的对象
     * @return 生成的Json对象
     */
    public String toJson(Object o) {
        return provider.toJson(o);
    }
}
