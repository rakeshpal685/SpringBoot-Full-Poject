package com.java.fullProject.someConceptTesting.springActuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

/*By default after spring 2.2.x the HttpTraceRepository endpoint is desabled, If we want to enable it then we can do it like this
In the configuration class, create a bean of httpTraceRepository()
@Bean
public HttpTraceRepository httpTraceRepository(){
    return new InMemoryHttpTraceRepository();
    }
    This uses the in memory to store the recent 100 http requests that are made.*/
@Component
@Endpoint(id = "myEndpoint") // This is my specific endpoint
public class MyCustomEndpoint {

/*    @ReadOperation
    public String getInfo(String name) { //http://localhost:8080/rakesh/myEndpoint?name=kuch bhi
        return "company name is : " + name;
    }

    @ReadOperation
    public String getInformation(@Selector String name) {//localhost:8080/rakesh/my-Endpoint/kuch bhi 2
        return "Mr Arnab said  : " + name;
    }*/

    @ReadOperation//This is for GET request
    public ActuatorResponse getApplicationInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Rakesh Application");
        map.put("description", " My description");
        map.put("version", 1.5);

        ActuatorResponse actuatorResponse = new ActuatorResponse();
        actuatorResponse.setDetails(map);
        return actuatorResponse;
    }

    @WriteOperation//This is for POST request
    public String persistOperation(@Selector String name){
        //Write our persist logic here
        return "name "+name+" is added";
    }

    @DeleteOperation//This is for DELETE request
    public String deleteOperation(@Selector String name){
        //Write our delete logic here
        return null;
    }
}
