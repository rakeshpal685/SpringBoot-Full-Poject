package com.java.fullProject.someConceptTesting.springSecurityJWT.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
This model is to map the json object sent to us with this POJO, So that we can convert the json to object
and use the fields of it.
*/
@Data
public class StudentRequestModel {

/*When the Json is coming to us, we will validate that the field data is json is present or not,
for that we will use the below annotations to check like @NotNull, @Size*/
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 12, message="Password must be at least 8 characters")
    private String password;

}
