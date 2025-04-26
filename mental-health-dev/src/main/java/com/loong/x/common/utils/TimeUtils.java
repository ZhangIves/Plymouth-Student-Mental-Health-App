package com.loong.x.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Description
 * 当前时间获取工具（方便调试打印）
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/9 13:59
 */
public class TimeUtils {
    public static final Logger log = LoggerFactory.getLogger(TimeUtils.class);

    public TimeUtils() {
    }

    public static String getCurrentTime() {
        Date date = new Date();
        //格式化时间
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDate dateTime = LocalDate.parse("2018-06-16 10:00:00", formatter);
        //SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String tmp_time = sdf2.format(createTime);
        //Date date = DateUtils.parseDate(tmp_time, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String currentTime = sdf.format(date);
        System.out.println("当前时间：" + currentTime);
        return currentTime;
    }

    public static String getEndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println("结束时间：" + sdf.format(date) + "------------------------------------------");
        return sdf.format(date);
    }

    public static String formatter(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * String 类型的时间戳
     *
     * @return 时间戳
     */
    public static String getTimestamp() {
        return String.valueOf(Instant.now().toEpochMilli());

    }

}
