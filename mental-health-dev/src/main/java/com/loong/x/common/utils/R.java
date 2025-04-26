package com.loong.x.common.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * 通用返回结果类 服务器最终响应是数据
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/1 13:56
 */
@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String message; //错误信息

    private T data; //返回数据

    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> success(Integer code, String msg, T data) {
        R<T> r = new R<T>();
        r.code = code;
        r.message = msg;
        r.data = data;
        return r;
    }

    public static <T> R<T> success(Integer code,String msg) {
        R<T> r = new R<T>();
        r.code = code;
        r.message = msg;
        r.data = null;
        return r;
    }

    public static <T> R error(String message) {
        R r = new R();
        r.message = message;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
