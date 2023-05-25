package com.java.fullProject.service;

import com.java.fullProject.EmployeeModel.EmployeesResponse;
import com.java.fullProject.entity.Employees;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

   Employees saveEmployees(Employees employees);

    List<EmployeesResponse> getAllEmployees();

    EmployeesResponse getEmployeeById(int id);

  Employees updateEmployee(Employees employees, int id);

  void deleteEmployee(int id);

  List<Employees> employeesPageable(Pageable pageable);
}
