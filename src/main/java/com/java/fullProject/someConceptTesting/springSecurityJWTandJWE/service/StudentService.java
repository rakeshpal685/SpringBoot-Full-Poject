package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.service;

import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.dto.StudentDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface StudentService extends UserDetailsService {

  StudentDTO createStudent(StudentDTO studentDTO);

 StudentDTO getUser(String userName);

  
}
