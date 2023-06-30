/*Here I can put any create table query, if the file name is schema-h2.sql,
 it will be automatically picked up during server up if we are using embedded DB,
 But for external DB we have to use the below property
 spring.sql.init.mode=always If my sql file name is different then default then I can use below property
 spring.sql.init.schema-locations=classpath:xyz.sql
  Here I am naming the file as schema-mysql so that I can use this specific file in my mysql.properties file*/