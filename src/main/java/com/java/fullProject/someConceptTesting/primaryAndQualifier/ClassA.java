package com.java.fullProject.someConceptTesting.primaryAndQualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Component("blahBlah"), if we write this then the bean of ClassA will be referenced with blahBlah name.
@Primary
/*When we have multiple implementations then which one to inject is decided by primary,we can use
@Qualifier too at the place where we are using the interface, like
@Autowired
@Qualifier("classB")//Here we have to give the bean name which is by default camelcase in nature, unless the nameis modified.
MyInterface myInterface*/
public class ClassA implements MyInterface2 {
    @Override
    public void sound() {
        System.out.println("This is Class A implementation");
    }
}
