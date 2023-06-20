package com.java.fullProject.consumingAPIUsingRestTemplate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

       /* //This block of code is used to set timeout function when we are hitting external URI.
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(2000);
        simpleClientHttpRequestFactory.setReadTimeout(3000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);*/


        /*setErrorHandler() is used to catch exceptions and navigate it to my exception class, while doing a REST call.
        restTemplate.setErrorHandler(new MyExceptionHandlingClass());*/
        return restTemplate;
    }
}
