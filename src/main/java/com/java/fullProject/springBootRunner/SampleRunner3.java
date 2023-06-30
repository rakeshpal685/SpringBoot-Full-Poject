package com.java.fullProject.springBootRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*We can have multiple runners, we have to annotate the class with @component to make it a spring bean,
We have used @Order to tell in which order the runners should be executed, if it is not given then the runners
will be executed in alphabetical order of the class name*/
@Component
@Order(1)//If we have multiple runners then this decides in which order they will run
public class SampleRunner3 implements ApplicationRunner {
    //We can use commandLineRunner too, but it is now legacy
//public class SampleRunner3 implements CommandLineRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
/*If we are using commandLineRunner then we have to use the below arguments for the run method,if we are
passing any arguments to our main method then they will come here directly if we are using commandLineRunner
public void run(String... args) throws Exception {*/

        System.out.println("This is first command line runner");
    }


/*    Using Runners we can pass two types of arguments, option arguments and non-option arguments,In Commandline\
    runner, we can't distinguish between the types of the arguments, but using ApplicationRunner we have methods that
    helps us to differentiate between the two different types of the arguments, option arguments are passed in the form of key:value

   (java -jar filename.jar --server.port=9090 --spring.main.banner-mode=off// here we have provided two
    option arguments, which is denoted by --, similarly we can run using maven also,
    mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=9090 --spring.main.banner-mode=off 'male' 'india'"

    we can use the arguments in our code also. eg:-java -jar filename.jar --customer.name=John, --customer.age=30 "male" "india"
    here we have two option arguments and male and india as non option argument

ApplicationArgument object can be used to access the option arguments and non-option arguments,
ApplicationArguments object has methods like,
getNonOptionArgs()-- returns non-option args as a list
get0ptionNames( )--returns a set of names of the keys of option arguments
get0ptionValues(key)-- returns a list of values of an option argument


            */

}
