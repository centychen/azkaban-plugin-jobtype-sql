package org.cent.azkaban.plugin.sql;

import azkaban.jobExecutor.AbstractJob;
import azkaban.utils.Props;
import azkaban.utils.PropsUtils;
import com.alibaba.fastjson.JSON;
import org.cent.azkaban.plugin.sql.service.ExecuteJobService;
import org.cent.azkaban.plugin.sql.service.impl.ExecuteJobServiceImpl;
import org.cent.azkaban.plugin.sql.exception.SqlJobProcessException;
import org.apache.log4j.Logger;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: SQl脚本调度任务实现主类
 */
public class SqlJob extends AbstractJob {
    /**
     * azkaban系统参数
     */
    private Props sysProps;
    /**
     * job配置参数
     */
    private Props jobProps;
    /**
     * 任务调度类
     */
    private ExecuteJobService executeJobService;

    public SqlJob(String jobid, Props sysProps, Props jobProps,
                  Logger logger) {
        super(jobid, logger);

        this.sysProps = sysProps;
        this.jobProps = jobProps;
        this.executeJobService = new ExecuteJobServiceImpl();
    }

    /**
     * 执行调度任务
     *
     * @throws Exception
     */
    @Override
    public void run() throws Exception {

        getLog().info("[sql_job]sysProps:" + sysProps.toString());
        getLog().info("[sql_job]jobProps:" + jobProps.toString());

        //转化任务参数键值对
        try {
            this.resolveProps();
        } catch (Exception var22) {
            this.handleError("[sql_job]Bad property definition! " + var22.getMessage(), var22);
        }

        //执行任务
        try {
            executeJobService.executeJob(this.jobProps, this.getLog());
        } catch (SqlJobProcessException e) {
            handleError(e.getMessage(), e);
        }

    }

    /**
     * 处理异常信息
     *
     * @param errorMsg
     * @param e
     * @throws Exception
     */
    protected void handleError(String errorMsg, Exception e) throws Exception {
        this.error(errorMsg);
        if (e != null) {
            throw new Exception(errorMsg, e);
        } else {
            throw new Exception(errorMsg);
        }
    }

    /**
     * 转换参数
     */
    protected void resolveProps() {
        this.jobProps = PropsUtils.resolveProps(this.jobProps);
    }

    public Props getSysProps() {
        return sysProps;
    }

    public void setSysProps(Props sysProps) {
        this.sysProps = sysProps;
    }

    public Props getJobProps() {
        return jobProps;
    }

    public void setJobProps(Props jobProps) {
        this.jobProps = jobProps;
    }

    public ExecuteJobService getExecuteJobService() {
        return executeJobService;
    }

    public void setExecuteJobService(ExecuteJobService executeJobService) {
        this.executeJobService = executeJobService;
    }
}
