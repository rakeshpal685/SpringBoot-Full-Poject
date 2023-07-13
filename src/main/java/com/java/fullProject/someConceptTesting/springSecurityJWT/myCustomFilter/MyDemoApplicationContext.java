package com.java.fullProject.someConceptTesting.springSecurityJWT.myCustomFilter;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*If we are not using spring stereotype annotations to create a bean of any class then spring won't create
a bean for us, but let's say we want to autowire a dependency in that class then spring won't do that
because as spring is not creating a bean for the main class then it won't inject the dependency while creating
the class, but we need that dependency injected so that we can use the variable later, to do so we can
get the bean using the below approach.Here I am creating my own class and initializing the ApplicationContext
to get the bean by passing the bean name and ask applicationContext.getBean(beanName)
Here we can either Autowired the ApplicationContext or implement ApplicationContextAware*/
@Component
public class MyDemoApplicationContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //Instead of this we can autowire ApplicationContext too.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    MyDemoApplicationContext.applicationContext =applicationContext;
    }
//    Here we can call this static method from outside by passing the beanName to get the bean
    public static Object getBean(String beanName){
       return applicationContext.getBean(beanName);
    }

}
