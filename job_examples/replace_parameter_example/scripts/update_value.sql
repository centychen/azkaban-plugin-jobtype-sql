update ${sql_job.tableName} set value = value*2;

select count(*) from ${sql_job.tableName};
