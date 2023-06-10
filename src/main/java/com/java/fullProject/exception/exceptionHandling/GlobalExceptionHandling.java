package com.java.fullProject.exception.exceptionHandling;


import com.java.fullProject.exception.customException.EmployeeErrorResponse;
import com.java.fullProject.exception.customException.EmployeeNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandling {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException e) {
        log.error(e);
        //return new ResponseEntity<String>("The element is not present", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The element is not present");
    }

//    @ExceptionHandler(value = ResourceNotFound.class)
//    public ResponseEntity<String> handle(ResourceNotFound e) {
//        log.error(e);
//        // return new ResponseEntity<String>("The resource is not present", HttpStatus.NOT_FOUND);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The resource is not present");
//    }

    @ExceptionHandler
/*            (value = EmployeeNotFound.class)
    This value is not necessary, the type of exception handled is defined by the parameter of the handling
    method. Here in this case we are handling the exception of type EmployeeNotFound.*/
    public ResponseEntity<EmployeeErrorResponse> handle(EmployeeNotFound e) {
        EmployeeErrorResponse response = new EmployeeErrorResponse();
        response.setErrorMessage(e.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setErrorClass(e.getClass().getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

}