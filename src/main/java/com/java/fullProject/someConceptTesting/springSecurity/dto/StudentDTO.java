package com.java.fullProject.someConceptTesting.springSecurity.dto;

import lombok.Data;

@Data
public class StudentDTO {
//  This will contain fields from both DB and Json

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String encryptedPassword;
  private String userId;
}
