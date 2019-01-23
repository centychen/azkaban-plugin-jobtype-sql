# Azkaban SQL jobtype

## 功能说明


## 安装部署
- 下载最新发行版本，如：`azkaban-plugin-jobtype-sql-1.0.1.RELEASE.zip`
下载地址：[https://gitee.com/centy/azkaban-plugin-jobtype-sql/releases](https://gitee.com/centy/azkaban-plugin-jobtype-sql/releases)

- 将`azkaban-plugin-jobtype-sql-1.0.1.RELEASE.zip`拷贝至`${AzkabanHome}/plugins/jobtypes/`目录下，直接解压到当前目录，
解压后目录结构如下：
    - plugins
        - sql_job 
            - lib
                - ant-1.10.5.jar
                - ant-launcher-1.10.5.jar
                - azkaban-plugin-jobtype-sql-1.0.1.RELEASE.jar
                - fastjson-1.2.23.jar
                - mysql-connector-java-5.1.38.jar
                - postgresql-42.2.45.jar
            - private.properties

- 重启Azkaban完成安装。

## 插件配置参数
### Flow参数
Flow参数可配置在Flow文件内，也可定义在系统环境变量、Azkaban全局参数、Azkaban控制台定义的Flow Parameter等，具体参考Azkaban的配置读取优先级。

Azkaban文档地址：[https://azkaban.readthedocs.io/en/latest/getStarted.html](https://azkaban.readthedocs.io/en/latest/getStarted.html)

参数|说明
--|--
sql_job.database.type|数据库类型，支持mysql/postgresql 
sql_job.database.driver|数据库驱动类com.mysql.jdbc.Driver
sql_job.database.host|数据库主机地址
sql_job.database.port|数据库端口
sql_job.database.database|数据库名称
sql_job.database.schema|实例或模式名称，postgresql数据库必填
sql_job.database.username|数据库用户名
sql_job.database.password|数据库密码

### Job参数
Job参数定义在Job节点内，用于配置每个Job的定义。

参数|说明
--|--
sql_job.scripts|SQL脚本路径，支持绝对路径和相对路径。相对路径是指SQL文件相对project根目录（即上传至Azkaban的zip压缩包的根目录）的路径。<br>支持多个路径，多个通过英文逗号进行分隔。

## 使用说明
**本文示例基于Azkaban Flow 2.0，Flow 1.0插件也能支持。**

### 快速开始


### 使用占位符参数

## 注意事项
- sql解释执行使用ant.jar包，该包不支持"#"号格式的注释，只支持"--"或"//"格式注释。
- 占位符参数名必须以"sql_job."作为前缀。

