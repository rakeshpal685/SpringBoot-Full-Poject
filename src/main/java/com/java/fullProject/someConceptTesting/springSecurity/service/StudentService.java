package com.java.fullProject.someConceptTesting.springSecurity.service;

import com.java.fullProject.someConceptTesting.springSecurity.dto.StudentDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface StudentService extends UserDetailsService {

  StudentDTO createStudent(StudentDTO studentDTO);

  
}
