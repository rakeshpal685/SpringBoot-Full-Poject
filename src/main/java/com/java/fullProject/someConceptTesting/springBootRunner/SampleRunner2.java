package com.java.fullProject.someConceptTesting.springBootRunner;

import com.java.fullProject.entities.hibernateInheritance.PlayerRepo;
import com.java.fullProject.someConceptTesting.logging.MyClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@PropertySource(value = {"classpath:application-otherthendefault.properties"})
/*@PropertySource tells spring boot to scan for the given properties files too along with the default
properties file, we can use Environment variable as below to get the values from the properties file*/
public class SampleRunner2 implements CommandLineRunner {

/*    @Autowired
   /private MyInterface1 myInterface ;
   set spring.profiles.active=classBcd in properties file*/

    @Autowired
    private Environment environment;

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private MyClass myClass;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is second command line runner");

        //myInterface.sound();

        //System.out.println(environment.getProperty("rakesh.details.company-details.established-on"));

       /* playerRepo.save(new Batsman(12, "Sachin", 500, 92, 300));
        playerRepo.save(new Batsman(1, "Rahul", 50, 56, 30));

        playerRepo.save(new Bowler(2, "irfan", 50, 42, "6/30"));
        playerRepo.save(new Bowler(16, "Azhar", 80, 92, "8/20"));*/

        //myClass.testMethod();


    }
}
