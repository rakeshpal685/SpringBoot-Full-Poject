package com.java.fullProject.exception.exceptionHandling;


import com.java.fullProject.exception.customException.ResourceNotFound;
import java.util.NoSuchElementException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class exceptionHandling {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException e) {
        log.error(e);
        return new ResponseEntity<String>("The element is not present", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<String> handle(ResourceNotFound e) {
        log.error(e);
         return new ResponseEntity<String>("The resource is not present", HttpStatus.NOT_FOUND);
    }
}
