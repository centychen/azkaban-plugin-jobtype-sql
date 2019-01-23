package org.cent.azkaban.plugin.sql.service.impl;

import azkaban.utils.Props;
import org.apache.log4j.Logger;
import org.cent.azkaban.plugin.sql.service.ExecuteJobService;
import org.cent.azkaban.plugin.sql.pojo.DatabasePojo;
import org.cent.azkaban.plugin.sql.constants.CommonConstants;
import org.cent.azkaban.plugin.sql.constants.JobPropsKey;
import org.cent.azkaban.plugin.sql.constants.SqlJobPropKeys;
import org.cent.azkaban.plugin.sql.exception.SqlJobProcessException;
import org.cent.azkaban.plugin.sql.util.BlankUtil;
import org.cent.azkaban.plugin.sql.util.SqlUtil;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: 业务服务实现类
 */
public class ExecuteJobServiceImpl implements ExecuteJobService {

    private Logger logger;

    /**
     * 执行调度任务
     *
     * @param jobProps
     * @param logger
     * @throws SqlJobProcessException
     */
    @Override
    public void executeJob(Props jobProps, Logger logger) throws SqlJobProcessException {
        this.logger = logger;
        //获取sql脚本配置
        List<String> sqlFilePaths = getSqlFilesFormProps(jobProps);
        if (BlankUtil.isEmpty(sqlFilePaths)) {
            this.logger.warn("任务未配置需要执行的SQL脚本文件！");
        }

        //循环执行配置SQL脚本
        for (String sqlFilePath : sqlFilePaths) {
            executeSingleSqlFile(jobProps, sqlFilePath);
        }

    }

    /**
     * 执行单个sql脚本文件
     *
     * @param jobProps
     * @param sqlFilePath
     * @throws SqlJobProcessException
     */
    private void executeSingleSqlFile(Props jobProps, String sqlFilePath) throws SqlJobProcessException {
        try {
            //加载配置的sql脚本文件
            File sqlFile = SqlUtil.loadSqlFile(sqlFilePath);

            //替换SQL脚本参数
            String sql = SqlUtil.file2String(sqlFile);
            Map<String, String> params = jobProps.getMapByPrefix(CommonConstants.CUSTOM_PREFIX);
            sql = SqlUtil.replacePlaceHolderForSQL(sql, params);

            //待执行的SQL脚本写入临时文件
            File execSqlFile = SqlUtil.generateTempSqlFileForExecute(sql, jobProps.get(JobPropsKey.WORKING_DIR.getKey()), sqlFile.getName());

            //执行SQL脚本
            DatabasePojo databasePojo = new DatabasePojo(jobProps);
            logger.info("[sql_job]Database URL:" + databasePojo.getUrl());
            logger.info("[sql_job]Database USER:" + databasePojo.getUsername());
            logger.info("[sql_job]  ");
            logger.info("[sql_job]================= execute sql scripts ===================");
            logger.info("[sql_job]\r\n" + sql);
            logger.info("[sql_job]================= execute sql scripts ===================");
            logger.info("[sql_job]  ");

            String logPath = genExecuteSqlLogPath(jobProps.get(JobPropsKey.JOB_ATTACHMENT_FILE.getKey()));
            SqlUtil.executeSQL(execSqlFile, databasePojo, logPath);

        } catch (Exception e) {
            //获取job基本信息
            String projectName = jobProps.get(JobPropsKey.FLOW_PROJECTNAME.getKey());
            String flowId = jobProps.get(JobPropsKey.FLOW_FLOWID.getKey());
            String execId = jobProps.get(JobPropsKey.FLOW_EXECID.getKey());

            SqlJobProcessException e1 = new SqlJobProcessException(e.getMessage(), projectName, flowId, execId, e);
            logger.error("[sql_job]"+e.getMessage(), e);
            throw e1;
        }
    }

    /**
     * 生成log路径
     *
     * @param attachFile
     * @return
     */
    private String genExecuteSqlLogPath(String attachFile) {
        return attachFile.substring(0, attachFile.lastIndexOf(CommonConstants.ATTACH_FILE_SUFFIX))
                + CommonConstants.LOG_FILE_SUFFIX;
    }

    /**
     * 获取配置sql脚本文件路径
     *
     * @param jobProps
     * @return
     */
    public List<String> getSqlFilesFormProps(Props jobProps) {
        List<String> scripts = new LinkedList<>();
        String scriptsString = jobProps.getString(SqlJobPropKeys.SQL_JOB_SCRIPTS.getKey());

        //判空
        if (BlankUtil.isEmpty(scriptsString)) {
            return scripts;
        }

        //脚本文件拆分
        String[] scriptArr = scriptsString.split(CommonConstants.SCRIPT_SPLIT_SYMBOL);
        scripts.addAll(
                Arrays.asList(scriptArr)
        );

        return scripts;
    }
}
