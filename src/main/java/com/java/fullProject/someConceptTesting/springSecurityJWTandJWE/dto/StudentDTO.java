package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
//  This will contain fields from both DB and Json

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String encryptedPassword;
  private String userId;
  private List<AuthorityDTO> roles;
}
