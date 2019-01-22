package org.cent.azkaban.plugin.sql.service;

import azkaban.utils.Props;
import org.cent.azkaban.plugin.sql.exception.SqlJobProcessException;
import org.apache.log4j.Logger;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description:
 */
public interface ExecuteJobService {
    /**
     * 执行SQL调度任务
     * @param jobProps
     * @param logger
     * @throws SqlJobProcessException
     */
    void executeJob(Props jobProps,Logger logger) throws SqlJobProcessException;
}
