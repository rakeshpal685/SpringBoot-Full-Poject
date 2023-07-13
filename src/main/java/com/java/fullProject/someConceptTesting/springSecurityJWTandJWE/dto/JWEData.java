package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto;

import lombok.Data;

@Data
public class JWEData {

private String issuer;
private String subject;
private String audience;
private String userId;
private String email;
private String rolesString;
}
