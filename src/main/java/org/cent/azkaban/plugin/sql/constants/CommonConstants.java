package org.cent.azkaban.plugin.sql.constants;

import java.time.ZoneId;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 通用常量类
 */
public enum CommonConstants {
    ;
    /**
     * GMT时间格式字符串
     */
    public static final String GMT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
    /**
     * 北京时区
     */
    public static final ZoneId BEIJING_TIME_ZONE = ZoneId.of("Asia/Shanghai");
    /**
     * sql脚本后缀
     */
    public static final String SQL_FILE_SUFFIX = ".sql";
    /**
     * log文件后缀
     */
    public static final String LOG_FILE_SUFFIX = ".log";
    /**
     * attach文件后缀
     */
    public static final String ATTACH_FILE_SUFFIX = ".attach";
    /**
     * 默认读取buffer区大小
     */
    public static final Integer DEFAULT_BUFFER_SIZE = 1024;
    /**
     * 默认编码方式
     */
    public static final String DEFAULT_ENCODING = "utf8";
    /**
     * 临时sql文件名称
     */
    public static final String TEMP_SQL_FILE_NAME_PREFIXX = "_temp_exec_sql";

    /**
     * 自定义参数前缀
     */
    public static final String CUSTOM_PREFIX = "sql_job";

    /**
     * 脚本文件分隔符
     */
    public static final String SCRIPT_SPLIT_SYMBOL = ",";

    /**
     * 文件路径分隔符
     */
    public static final String PATH_SPLIT_SYMBOL = "/";
}
