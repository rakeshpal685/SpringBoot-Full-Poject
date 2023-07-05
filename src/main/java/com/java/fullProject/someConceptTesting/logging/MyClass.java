package com.java.fullProject.someConceptTesting.logging;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MyClass {

  //Logger logger= LoggerFactory.getLogger(MyClass.class);//for slf4j
    private static final Logger LOGGER = LogManager.getLogger(MyClass.class);//This is for log4j2


  public void testMethod(){
      LOGGER.trace("A TRACE Message");
      LOGGER.debug("A DEBUG Message");
      LOGGER.info("An INFO Message");
      LOGGER.warn("A WARN Message");
      LOGGER.error("An ERROR Message");


  }
}
