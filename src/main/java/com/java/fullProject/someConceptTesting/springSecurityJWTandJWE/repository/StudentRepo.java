package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.repository;

import com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Students,Integer> {

    Students findByEmail(String email);

    Students findByFirstName(String firstName);
}
