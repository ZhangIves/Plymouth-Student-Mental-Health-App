package com.loong.x.common.exception;

/**
 * 自定义业务异常类
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/16 08:40
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
