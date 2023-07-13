package com.java.fullProject.someConceptTesting.springSecurityJWT.repository;

import com.java.fullProject.someConceptTesting.springSecurityJWT.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Students,Integer> {

    Students findByEmail(String email);

    Students findByFirstName(String firstName);
}
