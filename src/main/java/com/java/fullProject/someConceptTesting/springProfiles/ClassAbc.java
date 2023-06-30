package com.java.fullProject.someConceptTesting.springProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("MyClassAbc")
/*In my application.properties when I set spring.profiles.active=MyClassAbc, then this bean's properties
will be picked up, in fact any class that have @Profile("MyClassAbc") will be picked up when the given
profile is active, even I can create a @Bean manually and add @Profile() on it.*/

public class ClassAbc implements MyInterface1{
    @Override
    public void sound() {
        System.out.println("This is Class A implementation");
    }
}
