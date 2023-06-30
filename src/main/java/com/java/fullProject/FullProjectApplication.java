package com.java.fullProject;

import com.java.fullProject.someConceptTesting.MyOwnPropertiesForApplicationPropertiesFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value ={MyOwnPropertiesForApplicationPropertiesFile.class})//This annotation enables scanning for my custom properties class
/*The @SpringBootApplication annotation is there to configure three things. They are,
Enabling or disabling Spring Boot auto-configuration. (@EnableAutoConfiguration)
choosing which packages to scan for components (@ComponentScan)
Enabling the configuration bean registrations or for importing additional configurations (@SpringBootConfiguration)
Basically, The @SpringBootApplication is a short way to define @SpringBootConfiguration, @EnableAutoConfiguration,
and @ComponentScan together.*/
//@EnableSwagger2
public class FullProjectApplication {

	public static void main(String[] args) {
	SpringApplication.run(FullProjectApplication.class, args);
		/*Spring IoC container is responsible for instantiating, wiring, configuring,and managing the
entire life cycle of objects. BeanFactory and ApplicationContext represent the Spring IoC Containers,
and they are interfaces. BeanFactory provides basic functionalities and is recommended to use for
lightweight applications like mobile and applets. ApplicationContext provides basic features in
addition to enterprise-specific functionalities. ApplicationContext Implementation Classes are below,
AnnotationConfigApplicationContext container
AnnotationConfigWebApplicationContext
XmlWebApplicationContext
Based on the type of dependencies we have,the run method internally will create which type of context we need.
It takes 2 arguments, our main class and any arguments we pass to the main method during runtime */
	}

/*	We can create our own bean here. While creating our own bean we have command on our bean creation.
	Like we can specify when the bean is created on which condition.
	@ConditionalOnClass(xyz.class)// when this class bean is present in container
	@ConditionalOnProperty(name = "abc" , value = "true")//When this condition is true in properties file
	@Bean
	public ClassName referenceName(){
		return new ClassName();
	}*/


}

