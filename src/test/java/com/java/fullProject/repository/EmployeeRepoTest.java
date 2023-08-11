package com.java.fullProject.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.java.fullProject.entities.Employees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

@SpringBootTest
class EmployeeRepoTest {
  @Autowired
  EmployeeRepo employeeRepo;

  @Test
  void findAllByName() {
    Slice<Employees> rakesh = employeeRepo.findAllByEmName("Rakesh", PageRequest.of(0, 2));
    rakesh.forEach(System.out::println);
  }
}
