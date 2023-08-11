package com.java.fullProject.configurations.MultipleDB;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//We have to configure two different configurations, repositories and entities in 2 different packages
@Configuration
//used to allow the usage of annotation-driven transaction management capability.
@EnableTransactionManagement
//since we are using spring data jpa, this annotation is required to tell Spring to enable JPA repositories.
//We specified the entityManagerFactory and the transactionManager beans to be used in the JPA repositories.
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",//We have created entityManagerFactory below
// This is the package where we can have multiple repositories related to multiple Tables in our DB1
    basePackages = {"com/java/fullProject/repository"})
public class Db1Configuration {
  //  Here I am creating the Datasource, and telling spring to read the values from the properties
  // file for the datasource (Similar to hibernate.cfg.xml)
  //  Here I am telling that this is my primary Bean for datasource
  @Primary
  @Bean(name = "dataSource")
//This annotation tells spring to pick up the data source properties that are prefixed with "spring.db1.datasource"
//from the application.properties file and build a data source using DataSourceBuilder.
  @ConfigurationProperties(prefix = "spring.db1.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }
  
//  Here I am creating an entityManagerFactory (same as Sessionfactory) by injecting DataSource
  @Primary
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      //EntityManagerFactoryBuilder builder,
      @Qualifier("dataSource") DataSource dataSource) {
    
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");
    
/*   return builder
        .dataSource(dataSource)
        .properties(properties)
       //path of the entities for my DB1
        .packages("com/java/fullProject/entities")
        .persistenceUnit("db1")
        .build();*/
    
    entityManagerFactoryBean.setDataSource(dataSource);
    entityManagerFactoryBean.setPackagesToScan("com/java/fullProject/entities");
    entityManagerFactoryBean.setJpaPropertyMap(properties);
    entityManagerFactoryBean.setPersistenceUnitName("db1");
    
    return  entityManagerFactoryBean;
  }

//  Here I am creating TransactionManager by injecting the entityManagerFactory
  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
