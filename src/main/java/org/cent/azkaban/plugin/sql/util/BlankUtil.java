package org.cent.azkaban.plugin.sql.util;

import java.util.Collection;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 判空工具类
 */
public enum BlankUtil {
    ;

    /**
     * 集合判空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        if (collection == null) {
            return true;
        } else if (collection.size() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符串判空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 字符串非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
