package com.java.fullProject.springBootRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*We can have multiple runners, we have to annotate the class with @component to make it a spring bean,
We have used @Order to tell in which order the runners should be executed, if it is not given then the runners
will be executed in alphabetical order of the class name*/
@Component
@Order(1)
public class SampleRunner3 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is first command line runner");
    }
}
