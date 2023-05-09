package com.java.fullProject.service;

import com.java.fullProject.entity.Employees;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

   Employees saveEmployees(Employees employees);

   List<Employees> getAllEmployees();

  Employees getEmployeeById(int id);

  Employees updateEmployee(Employees employees, int id);

  void deleteEmployee(int id);

  List<Employees> employeesPageable(Pageable pageable);
}
