package org.cent.azkaban.plugin.sql.constants;

import org.cent.azkaban.plugin.sql.util.BlankUtil;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 数据库类型定义
 */
public enum DatabaseTypes {
    /**
     * Mysql数据库
     */
    mysql("com.mysql.jdbc.Driver"),
    /**
     * Postgresql数据库
     */
    postgresql("org.postgresql.Driver");

    /**
     * 数据库驱动
     */
    private String driver;

    DatabaseTypes(String driver) {
        this.driver = driver;
    }

    public String getDriver() {
        return driver;
    }

    /**
     * 拼接JDBC连接串。
     * - mysql：jdbc:mysql://host:port/database
     * - postgresql：jdbc:postgresql://host:port/database?currentSchema=schema
     *
     * @param host
     * @param port
     * @param database
     * @param schema
     * @return
     */
    public String jointJDBCUrl(String host, Integer port, String database, String schema) {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc");
        sb.append(":");
        sb.append(this.name());
        sb.append("://");
        sb.append(host);
        sb.append(":");
        sb.append(port);
        sb.append("/");
        sb.append(database);

        //postgresql 需指定schema
        if (DatabaseTypes.postgresql.equals(this)
                && BlankUtil.isNotEmpty(schema)) {
            sb.append("?currentSchema=");
            sb.append(schema);
        }

        return sb.toString();
    }
}
