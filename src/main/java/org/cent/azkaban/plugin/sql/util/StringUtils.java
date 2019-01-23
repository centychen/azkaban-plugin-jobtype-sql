package org.cent.azkaban.plugin.sql.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.cent.azkaban.plugin.sql.constants.CommonConstants.PATH_SPLIT_SYMBOL;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 字符串工具类
 */
public enum StringUtils {
    ;
    private static Pattern NUMERIC_PATTERN = Pattern.compile("[0-9]*");

    private static Pattern WINDOWS_ABSOLUTE_PATA_PATTERN = Pattern.compile("^[\\s\\S]{1}:[\\s\\S]*$");

    /**
     * 判断字符串是否数值
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = NUMERIC_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否绝对路径
     *
     * @param path
     * @return 是返回true，否则返回false
     */
    public static boolean isAbsolutePath(String path) {
        if (BlankUtil.isEmpty(path)) {
            return false;
        }

        if (path.indexOf(PATH_SPLIT_SYMBOL) == 0) {
            //linux绝对路径
            return true;
        } else if (WINDOWS_ABSOLUTE_PATA_PATTERN.matcher(path).matches()) {
            //windows绝对路径
            return true;
        }

        return false;
    }
}
