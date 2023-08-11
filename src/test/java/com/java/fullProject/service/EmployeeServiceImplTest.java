package com.java.fullProject.service;

import static org.junit.jupiter.api.Assertions.*;

import com.java.fullProject.entities.Employees;
import com.java.fullProject.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

@SpringBootTest
class EmployeeServiceImplTest {
    
    @Autowired
    EmployeeRepo employeeRepo;
    
    @Test
    void employeesPageable() {
        Slice<Employees> all = employeeRepo.findAll(PageRequest.of(0, 2));
        //Page<Employees> all = employeeRepo.findAll(PageRequest.of(0, 2));
        //List<Employees> all = employeeRepo.findAll(PageRequest.of(0, 2)).getContent();
        all.forEach(System.out::println);
    }
}