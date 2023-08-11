package com.java.fullProject.exception.customException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
//Either we can use @ResponseStatus or we can return the ResponseEntity with the status code in exception handling

public class EmployeeErrorResponse  extends RuntimeException{
/* Whenever we will get an EmployeeNotFound exception, then I want to send the error response json message
    back to the user containing the below fields   */
    

    private String errorMessage;
    private LocalDateTime dateTime;
    private String errorClass;
    private String myCustomErrorMessage;

/*
Sometimes in our response the stacktrace is also included because it is by default set to always, if we use
devtool, so to remove that we can use the below property in properties file.if it doesn't work, try overriding
the below method
server.error.include-stacktrace=never*/
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
