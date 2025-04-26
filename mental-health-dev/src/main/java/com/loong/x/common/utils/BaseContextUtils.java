package com.loong.x.common.utils;

/**
 * Description
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/4 10:14
 */
public class BaseContextUtils {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值 基于每一个线程之内
     *
     * @param id 用户id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }


    /**
     * 获取值
     *
     * @return 用户id
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

}
