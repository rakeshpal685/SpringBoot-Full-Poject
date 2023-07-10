package com.java.fullProject.someConceptTesting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/*@EnableConfigurationProperties(value ={MyOwnPropertiesForApplicationPropertiesFile.class}) write this on the main class,
add spring-boot-configuration-processor dependency too.
//@PropertySource(value = {"classpath:application-otherthendefault.properties"}) write this on the class where we want to load the properties from the specific file

@Autowire Environment variable and use it to get the value from the property file, see SpringBootRunner.

In second way, we can create a class here and create a @Bean of this class in @Configuration file and use @ConfigurationProperties on top of it.
this will only work with properties files but not with yml files
*/
@ConfigurationProperties(prefix = "rakesh.details")
@Data
public class MyOwnCustomPropertiesForApplication_PropertiesFile {

    /**
     * Name of the server. This is documentation for this property, when we will use this property in
     * application.properties file, this things will pop up to show what this property is regarding.
     * For documentation use /** (Double star)
     */
    private String companyName = "KuchBhi";//This is the default name for the companyName if not overridden in application.properties

    /**
     * This is another documentation for the below property
     */
    private Company companyDetails;//These are nested properties, like rakesh.details.companyDetails.establishedOn

    private List<String> departments;//this can be defined as rakesh.details.departments[0]="abc" and so on.

    //We can create a separate file for the below class too or keep it nested like done here.
    @Data
    public static class Company {

        private int establishedOn;//rakesh.details.company-details.established-on,
        // it can work with _, all capital or as same as we have defined here in properties file.
        //rakesh.details.company_details.establishedOn

        private int numberOfEmployees;
    }

/*   If we don't have setter for our class then we can use constructor too. In this case we have to use
    @ConstructorBinding on our constructor and @EnableConfigurationProperties(value ={MyOwnPropertiesForApplicationPropertiesFile.class})
    on our main class.

    @ConstructorBinding
    public MyOwnPropertiesForApplicationPropertiesFile(String companyName, Company companyDetails, List<String> departments) {
        this.companyName = companyName;
        this.companyDetails = companyDetails;
        this.departments = departments;
    }*/

    //we can take boolean and Map too

/*    @Autowired
    Environment environment;
    environment.getProperty("nameOfTheProperty");*/
}