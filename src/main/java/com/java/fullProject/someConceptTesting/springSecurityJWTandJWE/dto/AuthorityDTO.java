package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto;

import lombok.Data;

@Data
public class AuthorityDTO {
    private int id;
    private String role;
    private StudentDTO student_id;
}
