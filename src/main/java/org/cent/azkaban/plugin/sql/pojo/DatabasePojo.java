package org.cent.azkaban.plugin.sql.pojo;

import azkaban.utils.Props;
import azkaban.utils.UndefinedPropertyException;
import org.cent.azkaban.plugin.sql.constants.DatabaseTypes;
import org.cent.azkaban.plugin.sql.constants.SqlJobPropKeys;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 数据库配置Bean
 */
public class DatabasePojo {
    /**
     * 数据库驱动类
     */
    private String driver;
    /**
     * 数据库连接串
     */
    private String url;
    /**
     * 登录用户
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;

    /**
     * 使用任务配置参数进行初始化
     *
     * @param jobProps
     */
    public DatabasePojo(Props jobProps) {
        DatabaseTypes type = DatabaseTypes.valueOf(jobProps.get(SqlJobPropKeys.SQL_JOB_DATABASE_TYPE.getKey()));
        String host = jobProps.getString(SqlJobPropKeys.SQL_JOB_DATABASE_HOST.getKey());
        Integer port = jobProps.getInt(SqlJobPropKeys.SQL_JOB_DATABASE_PORT.getKey());
        String database = jobProps.getString(SqlJobPropKeys.SQL_JOB_DATABASE_DATABASE.getKey());

        String schema = null;
        try {
            jobProps.getString(SqlJobPropKeys.SQL_JOB_DATABASE_SCHEMA.getKey());
        } catch (UndefinedPropertyException e) {
            //nothing to do
        }

        this.driver = type.getDriver();
        this.url = type.jointJDBCUrl(host, port, database, schema);
        this.username = jobProps.get(SqlJobPropKeys.SQL_JOB_DATABASE_USERNAME.getKey());
        this.password = jobProps.get(SqlJobPropKeys.SQL_JOB_DATABASE_PASSWORD.getKey());
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
