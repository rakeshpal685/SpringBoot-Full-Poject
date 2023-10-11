package com.java.fullProject.exception.exceptionHandling;

import com.java.fullProject.exception.customException.BusinessException;
import com.java.fullProject.exception.customException.EmployeeErrorResponse;
import com.java.fullProject.exception.customException.EmployeeNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandling {

  /*MethodArgumentNotValidException exception is thrown by java when we are trying to insert a data that doesn't pass the validation
  set in the entity class, Here @ExceptionHandler will see the type of exception , @ResponseStatus will see the
   response status thrown by the server, if both matches then this method will be executed*/
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> handle(MethodArgumentNotValidException e) {
    Map<String, String> errors = new HashMap<>();
    e.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              // Here fieldName is the name of the attribute in the entity class where
              // the validation is failed
              String fieldName = ((FieldError) error).getField();
              // errorMessage is the message we have given in the attribute in entity
              // class, in case the validation is failed
              String errorMessage = error.getDefaultMessage();
              // Here errors will return a key-value pair with all the validation errors.
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public ResponseEntity<String> handle(NoSuchElementException e) {
    log.error(e);
    // return new ResponseEntity<String>("The element is not present", HttpStatus.NOT_FOUND);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The element is not present");
  }

  @ExceptionHandler
  /*            (value = EmployeeNotFound.class)
  This value is not necessary, the type of exception handled is defined by the parameter of the handling
  method. Here in this case we are handling the exception of type EmployeeNotFound. Spring will see that
  we have a @ExceptionHandler method that means this method is handling an exception and based on the parameter
  of the method it sees that which type of exception this method is handling.
  We can use value if we don't want to fetch the exception values for e, rather than we will be hardcoding the
  values by our self and pass it as JSON to the client*/
  public ResponseEntity<EmployeeErrorResponse> handle(EmployeeNotFound e) {
    EmployeeErrorResponse response = new EmployeeErrorResponse();
    response.setErrorMessage(e.getMessage());
    response.setDateTime(LocalDateTime.now());
    response.setErrorClass(e.getClass().getSimpleName());
    response.setMyCustomErrorMessage(
        "My custom error message that is not coming from error object e");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(value = BusinessException.class)
  public ResponseEntity<EmployeeErrorResponse> handle(BusinessException e) {
    EmployeeErrorResponse response = new EmployeeErrorResponse();
    response.setErrorMessage(e.getMessage());
    response.setDateTime(LocalDateTime.now());
    response.setErrorClass(e.getClass().getSimpleName());
    response.setMyCustomErrorMessage("Please check the employee object data");
    return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(response);

    /*       public ResponseEntity<String> handle(BusinessException e) {
    log.error(e);
    return ResponseEntity.status(Integer.parseInt(e.getErrorCode())).body(e.getErrorMessage());*/
  }
}
