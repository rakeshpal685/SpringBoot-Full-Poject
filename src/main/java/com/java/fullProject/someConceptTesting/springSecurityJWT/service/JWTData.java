package com.java.fullProject.someConceptTesting.springSecurityJWT.service;

import lombok.Data;

@Data
public class JWTData {

private String issuer;
private String subject;
private String audience;
private String userId;
private String email;
private String rolesString;
}
