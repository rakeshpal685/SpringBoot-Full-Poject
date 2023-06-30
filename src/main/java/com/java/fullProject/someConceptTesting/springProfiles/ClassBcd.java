package com.java.fullProject.someConceptTesting.springProfiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("classBcd")
public class ClassBcd implements MyInterface1{
    @Override
    public void sound() {
        System.out.println("This is Class B implementation");
    }
}
