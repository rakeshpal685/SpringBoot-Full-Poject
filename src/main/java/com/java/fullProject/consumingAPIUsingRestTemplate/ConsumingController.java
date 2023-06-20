package com.java.fullProject.consumingAPIUsingRestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ConsumingController {

    private static final String url = "http://3rdPartyURL";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/particularData")
    public BindingClass getParticularDataFrom3rdParty() {
        BindingClass oneObject = restTemplate.getForObject(url, BindingClass.class);

        return oneObject;
    }

    @GetMapping("/allData")
    public List<BindingClass> getAllDataFrom3rdParty() {
        BindingClass[] allObjects = restTemplate.getForObject(url, BindingClass[].class);
        return Arrays.asList(allObjects);
    }

    @PostMapping("/postData")
    public BindingClass postDataFrom3rdParty(@RequestBody BindingClass bindingClassObject) {

        //Header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/Json");
        httpHeaders.add("Content-Type", "application/json");

        //Body
       /* BindingClass bindingClassObject = new BindingClass();
        bindingClassObject.setId(10);
        bindingClassObject.setName("Doe");
        bindingClassObject.setEmail("doe@example.com");
        We are getting the body in the PostMapping*/

        //request
        HttpEntity httpRequest = new HttpEntity(bindingClassObject, httpHeaders);

        BindingClass response = restTemplate.postForObject(url, httpRequest, BindingClass.class);
        return response;

    }

    /*    When we do a call to any API, alone with the body we will get many things back like cookies, Header,
        status code. using restTemplate.get/postForObject we wont get all these extra details and for that we havr to use
        get/postForEntity*/
    @GetMapping("/particularData/{api}/{id}")
    public ResponseEntity<BindingClass> getParticularDataFrom3rdPartyUsingGetForEntity(@RequestParam String api, @RequestParam int id) {
        ResponseEntity<BindingClass> responseEntity = restTemplate.getForEntity(url, BindingClass.class);
        /*here we have overloaded methods for getForEntity and postForEntity which takes uri variables, that means
if we have an uri where we can dynamically pass the values, we can do that from here.
eg- "http://xyz.com.feibjibc/{1stValue}/{2ndValue}, the overloaded methods takes varargs...
restTemplate.getForEntity(url, BindingClass.class,api,id)-as in the above method;

We can pass a lot of values in Map format to the uri
Map<String, Object> uriVariables = new HashMap<String, Object>();
uriVariables.put("api", "student");
uriVariables.put("id", 2);
restTemplate.getForEntity(url, BindingClass.class,uriVariables);*/

        HttpHeaders headers = responseEntity.getHeaders();
        System.out.println(headers.entrySet());

/* for (Map.Entry<String, List<String>> entries : headers.entrySet()) {
System.out.println("key + : " + entries.getKey() + "value : "+ entries.getValue());
}*/

        headers.forEach((key, value) -> System.out.println("key + : " + key +
                "value : " + value));


        HttpStatusCode code = responseEntity.getStatusCode();
        System.out.println(code);

        BindingClass bindingClass = responseEntity.getBody();
        System.out.println(bindingClass);

        return responseEntity;
    }

    //    Here this request will throw an error
    @GetMapping("/errorUri")
    public String errorUrl() {
        String url1 = "http://httpstat.us/400";

//        The error is handled by the MyExceptionHandlingClass
        restTemplate.setErrorHandler(new MyExceptionHandlingClass());

//        we are returning the response in plain String format
        String response = restTemplate.getForObject(url1, String.class);
        System.out.println(response);
        return response;
    }

}
