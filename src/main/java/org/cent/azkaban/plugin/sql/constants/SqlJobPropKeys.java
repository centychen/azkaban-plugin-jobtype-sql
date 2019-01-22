package org.cent.azkaban.plugin.sql.constants;

import static org.cent.azkaban.plugin.sql.constants.CommonConstants.CUSTOM_PREFIX;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description:
 */
public enum SqlJobPropKeys {
    /**
     * 数据库类型
     */
    SQL_JOB_DATABASE_TYPE(String.format("%s.database.type", CUSTOM_PREFIX)),
    /**
     * 数据库地址
     */
    SQL_JOB_DATABASE_HOST(String.format("%s.database.host", CUSTOM_PREFIX)),
    /**
     * 数据库地址
     */
    SQL_JOB_DATABASE_PORT(String.format("%s.database.port", CUSTOM_PREFIX)),
    /**
     * 数据库名称
     */
    SQL_JOB_DATABASE_DATABASE(String.format("%s.database.database", CUSTOM_PREFIX)),
    /**
     * 数据库实例（模式），针对postgresql
     */
    SQL_JOB_DATABASE_SCHEMA(String.format("%s.database.schema", CUSTOM_PREFIX)),
    /**
     * 用户名
     */
    SQL_JOB_DATABASE_USERNAME(String.format("%s.database.username", CUSTOM_PREFIX)),
    /**
     * 密码
     */
    SQL_JOB_DATABASE_PASSWORD(String.format("%s.database.password", CUSTOM_PREFIX)),
    /**
     * SQL脚本
     */
    SQL_JOB_SCRIPTS(String.format("%s.scripts", CUSTOM_PREFIX));

    private String key;

    SqlJobPropKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
