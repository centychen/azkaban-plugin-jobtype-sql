package org.cent.azkaban.plugin.sql.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.cent.azkaban.plugin.sql.constants.CommonConstants;
import org.cent.azkaban.plugin.sql.pojo.DatabasePojo;

import java.io.*;
import java.util.Map;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: SQL工具类
 */
public enum SqlUtil {
    ;

    /**
     * 加载sql脚本文件
     *
     * @param path
     * @return
     */
    public static File loadSqlFile(String path) {
        if (BlankUtil.isEmpty(path)) {
            throw new RuntimeException("SQL脚本文件路径未设置！");
        }

        File sqlFile = new File(path);
        if (!sqlFile.exists() || sqlFile.isDirectory()) {
            throw new RuntimeException(String.format("SQL脚本文件路径未正确设置或者文件不存在，当前设置路径：%s！", path));
        }

        String fileName = sqlFile.getName();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!CommonConstants.SQL_FILE_SUFFIX.equals(suffix)) {
            throw new RuntimeException(String.format("%s不是正确的SQL脚本文件！", fileName));
        }

        return sqlFile;
    }

    /**
     * 文件转字符串
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String file2String(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringWriter writer = new StringWriter();

        char[] buffer = new char[CommonConstants.DEFAULT_BUFFER_SIZE];
        int n;
        while (-1 != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
        }

        if (writer != null) {
            return writer.toString();
        } else {
            return null;
        }

    }

    /**
     * 替换变量
     *
     * @param sql
     * @param parameters
     * @return
     * @throws Exception
     */
    public static String replacePlaceHolderForSQL(String sql, Map<String, String> parameters) {
        for (String key : parameters.keySet()) {
            String placeHolder = String.format("\\$\\{%s\\%s\\}", CommonConstants.CUSTOM_PREFIX, key);
            sql = sql.replaceAll(placeHolder, parameters.get(key));
        }
        return sql;
    }

    /**
     * 写入当前任务执行的临时sql文件
     *
     * @param sql
     * @param workingDir
     * @param filename
     * @return
     * @throws Exception
     */
    public static File generateTempSqlFileForExecute(String sql, String workingDir, String filename) throws Exception {
        String fileName = genTempSqlFileName(workingDir, filename);

        File sqlFile;
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            sqlFile = new File(fileName);
            if (!sqlFile.exists()) {
                sqlFile.createNewFile();
            }

            writer = new BufferedWriter(new FileWriter(sqlFile));
            reader = new BufferedReader(new StringReader(sql));
            char[] buff = new char[CommonConstants.DEFAULT_BUFFER_SIZE];
            int n;
            while (-1 != (n = (reader.read(buff)))) {
                writer.write(buff, 0, n);
            }
            writer.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

        return sqlFile;
    }

    /**
     * 生成临时sql文件名称
     *
     * @param workingDir
     * @param filename
     * @return
     */
    private static String genTempSqlFileName(String workingDir, String filename) {
        return workingDir + "/" + CommonConstants.TEMP_SQL_FILE_NAME_PREFIXX
                + System.currentTimeMillis()
                + filename;
    }

    /**
     * 执行SQL脚本
     *
     * @param sqlFile      sql脚本文件
     * @param databasePojo 数据库配置
     * @param logfile      日志文件路径
     * @throws Exception
     */
    public static void executeSQL(File sqlFile, DatabasePojo databasePojo, String logfile) {
        SQLExec sqlExec = new SQLExec();
        sqlExec.setDriver(databasePojo.getDriver());
        sqlExec.setUrl(databasePojo.getUrl());
        sqlExec.setUserid(databasePojo.getUsername());
        sqlExec.setPassword(databasePojo.getPassword());
        sqlExec.setSrc(sqlFile);
        // 设置是否输出
        sqlExec.setPrint(true);
        sqlExec.setEncoding(CommonConstants.DEFAULT_ENCODING);
        sqlExec.setOutput(new File(logfile));
        sqlExec.setAppend(true);
        // 要指定这个属性，不然会出错
        sqlExec.setProject(new Project());
        sqlExec.setAutocommit(true);
        sqlExec.execute();
    }
}
