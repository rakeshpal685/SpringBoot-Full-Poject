package com.java.fullProject.exception.customException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeErrorResponse {
/* Whenever we will get an EmployeeNotFound exception, then I want to send the error response json message
    back to the user containing the below fields   */

    private String errorMessage;
    private LocalDateTime dateTime;
    private String errorClass;

}
