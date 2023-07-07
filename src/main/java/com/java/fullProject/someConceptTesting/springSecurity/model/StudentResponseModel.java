  package com.java.fullProject.someConceptTesting.springSecurity.model;

import lombok.Data;

/*
This model is to map the json object sent to us with this POJO, So that we can convert the json to object
and use the fields of it.
*/
@Data
public class StudentResponseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String userId;

}
