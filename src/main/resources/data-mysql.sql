/*Here I can put the queries regarding update table like inserting records,
if the file name is data.sql, it will be automatically picked up during server up
 But for external DB we have to use the below property
 spring.sql.init.mode=always
 If my sql file name is different then default then I can use below property in application.properties
 spring.sql.init.data-locations=classpath:xyz.sql
 -- here i am changing the name to data-sql, so that in my sql.properties file this will be picked up*/
