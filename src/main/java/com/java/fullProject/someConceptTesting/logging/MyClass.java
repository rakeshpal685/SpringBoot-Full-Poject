package com.java.fullProject.someConceptTesting.logging;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Log4j2
//@Slf4j
@Component
public class MyClass {
    
    /*   This is for slf4j, but we don't have to create a logger in this case,
   we can directly use log.trace/debug/info, like below
  Logger logger= LoggerFactory.getLogger(MyClass.class);//for slf4j*/
    //private static final Logger LOGGER = LogManager.getLogger(MyClass.class);//for log4j2
/*    Here we will give the class name so that we can see the logs are coming from which class, or
 else we can pass a string also, then that thing will be printer instead of my class name, here we
 don't have to create a logger in this case we can directly use log.trace/debug/info, like below,
 but if we want some other class name then we have to use LOGGER instead of log*/


  public void testMethod(){
/*      LOGGER.trace("A TRACE Message");
      LOGGER.debug("A DEBUG Message");
      LOGGER.info("An INFO Message");
      LOGGER.warn("A WARN Message");
      LOGGER.error("An ERROR Message");*/
      
      log.trace("A TRACE Message");
      log.debug("A DEBUG Message");
      log.info("An INFO Message");
      log.warn("A WARN Message");
      log.error("An ERROR Message");


  }
}
