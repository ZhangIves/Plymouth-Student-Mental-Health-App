package com.loong.x;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description
 * 启动类
 * @author wcloong
 * @version 0.1.0
 * @since 2024/3/27 11:23
 */
@MapperScan({"com.loong.x.app.mapper", "com.loong.x.app.dao","com.loong.x.chat.mapper"})
@SpringBootApplication
@Slf4j
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info("APP Start Success");
    }
}
