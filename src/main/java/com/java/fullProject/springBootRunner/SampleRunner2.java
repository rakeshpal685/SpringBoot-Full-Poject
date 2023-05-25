package com.java.fullProject.springBootRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner2 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("This is second command line runner");
    }
}
