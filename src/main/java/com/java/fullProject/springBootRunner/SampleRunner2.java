package com.java.fullProject.springBootRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@PropertySource(value ={"classpath:application-anything.properties"})
public class SampleRunner2 implements CommandLineRunner {

/*    @Autowired
   /private MyInterface1 myInterface ;
   set spring.profiles.active=classBcd in properties file*/

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is second command line runner");

        //myInterface.sound();

        System.out.println(environment.getProperty("rakesh.details.company-details.established-on"));

    }
}
