package com.java.fullProject.someConceptTesting.springActuator;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "my-Endpoint")//This is my specific endpoint
public class MyCustomEndpoint {

/*    @ReadOperation
    public String getInfo(String name) { //http://localhost:8080/rakesh/my-Endpoint?name=kuch bhi
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
