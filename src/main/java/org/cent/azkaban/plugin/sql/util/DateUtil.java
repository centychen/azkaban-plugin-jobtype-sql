package org.cent.azkaban.plugin.sql.util;

import org.cent.azkaban.plugin.sql.constants.CommonConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description:
 */
public enum DateUtil {
    ;


    /**
     * 获取当前时间（北京时间）
     *
     * @return
     */
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(CommonConstants.BEIJING_TIME_ZONE);
    }

    /**
     * GMT时间戳字符串转日期类型
     * @param timestamp 格式：2017-01-09T14:41:59.356+08:00
     * @return
     */
    public static LocalDateTime GmtTimeStr2LocalDateTime(String timestamp) throws ParseException {
        timestamp = timestamp.replaceAll(":[^:]*$", "00");
        DateFormat format=new SimpleDateFormat(CommonConstants.GMT_DATE_FORMAT);
        Date date=format.parse(timestamp);
        return  LocalDateTime.ofInstant(date.toInstant(),CommonConstants.BEIJING_TIME_ZONE);
    }
}
