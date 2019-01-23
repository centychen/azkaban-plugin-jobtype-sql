package org.cent.azkaban.plugin.sql.constants;

/**
 * @author: cent
 * @email: 292462859@qq.com
 * @date: 2019/1/22.
 * @description: azkaban job配置参数key
 */
public enum JobPropsKey {

    TYPE("type"),
    JVM_ARGS("jvm.args"),
    WORKING_DIR("working.dir"),
    PLUGIN_DIR("plugin.dir"),
    MEMORY_CHECK("azkaban.memory.check"),
    USER_TO_PROXY("user.to.proxy"),

    JOB_ID("azkaban.job.id"),
    JOB_ATTEMPT("azkaban.job.attempt"),
    JOB_ATTACHMENT_FILE("azkaban.job.attachment.file"),
    JOB_INNODES("azkaban.job.innodes"),
    JOB_OUTNODES("azkaban.job.outnodes"),
    JOB_METADATA_FILE("azkaban.job.metadata.file"),
    JOB_LOG_FILE("azkaban.job.log.file"),

    FLOW_START_TIMESTAMP("azkaban.flow.start.timestamp"),
    FLOW_START_TIMEZONE("azkaban.flow.start.timezone"),
    FLOW_START_YEAR("azkaban.flow.start.year"),
    FLOW_START_MONTH("azkaban.flow.start.month"),
    FLOW_START_DAY("azkaban.flow.start.day"),
    FLOW_START_HOUR("azkaban.flow.start.hour"),
    FLOW_START_MINUTE("azkaban.flow.start.minute"),
    FLOW_START_SECOND("azkaban.flow.start.second"),
    FLOW_START_MILLISECONDS("azkaban.flow.start.milliseconds"),

    FLOW_PROJECTID("azkaban.flow.projectid"),
    FLOW_PROJECTNAME("azkaban.flow.projectname"),
    FLOW_PROJECTVERSION("azkaban.flow.projectversion"),
    FLOW_FLOWID("azkaban.flow.flowid"),
    FLOW_UUID("azkaban.flow.uuid"),
    FLOW_EXECID("azkaban.flow.execid"),

    FLOW_PROJECTLASTCHANGEDBY("azkaban.flow.projectlastchangedby"),
    FLOW_PROJECTLASTCHANGEDDATE("azkaban.flow.projectlastchangeddate"),

    FLOW_SUBMITUSER("azkaban.flow.submituser");

    private String key;

    JobPropsKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
