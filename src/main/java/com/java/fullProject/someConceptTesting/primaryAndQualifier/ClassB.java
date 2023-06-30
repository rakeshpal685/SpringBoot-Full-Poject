package com.java.fullProject.someConceptTesting.primaryAndQualifier;

import org.springframework.stereotype.Component;

@Component
public class ClassB implements MyInterface2 {
    @Override
    public void sound() {
        System.out.println("This is Class B implementation");
    }
}
