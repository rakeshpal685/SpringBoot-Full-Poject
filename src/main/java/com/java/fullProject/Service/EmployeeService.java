package com.java.fullProject.Service;


import com.java.fullProject.Entity.Employees;

import java.util.List;

public interface EmployeeService {

    Employees saveEmployees(Employees employees);

    List<Employees> getAllEmployees();

    Employees getEmployeeById(int id);

    Employees updateEmployee(Employees employees, int id);

    void deleteEmployee(int id);

}
