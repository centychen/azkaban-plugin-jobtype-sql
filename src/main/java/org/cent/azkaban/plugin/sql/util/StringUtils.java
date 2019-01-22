package org.cent.azkaban.plugin.sql.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description:
 */
public enum StringUtils {
    ;
    private static Pattern NUMERIC_PATTERN = Pattern.compile("[0-9]*");
    /**
     * 判断字符串是否数值
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Matcher isNum = NUMERIC_PATTERN.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
