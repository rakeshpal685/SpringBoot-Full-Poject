package com.java.fullProject.someConceptTesting.springSecurity.repository;

import com.java.fullProject.someConceptTesting.springSecurity.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Students,Integer> {
}
