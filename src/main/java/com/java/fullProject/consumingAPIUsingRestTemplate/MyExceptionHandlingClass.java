package com.java.fullProject.consumingAPIUsingRestTemplate;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/*
We will use @RestControllerAdvice when we are developing the service, i.e if there is any error from our
side when client is querying for the service then we will send a json response to the client regarding the error.
But lets say we are consuming the service and from their end they haven't applied any error handling message
then we have to handle the error, in this case we will implement ResponseErrorHandler and override the methods,
*/

@Component
public class MyExceptionHandlingClass implements ResponseErrorHandler {
/*This method won't get called if there is any issue with the gateway or timeout issue. except that this
method will be called if there ia any other issues.*/
    ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
/*Spring will Check if there is any error in the response or not. i.e, anything apart from 200OK.
if there is any error then the flow will go the handleError method next*/
        System.out.println("Inside hasError method");

        return errorHandler.hasError(response);
    }

    //handle the exception, if there is any exception.
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Inside handleError method");
/*We can throw an exception and from controller as usual we can write @RestControllerAdvice and Global
exception handler and send our custom exception*/

/*        Properties properties = new Properties();
        properties.put("body", response.getBody());
        properties.put("status", response.getStatusCode());
        properties.put("headers", response.getHeaders());

        MyCustomException myCustomException = new MyCustomException();
        myCustomException.setProperties(properties);
        System.out.println(myCustomException);
        throw myCustomException;*/
    }
}
