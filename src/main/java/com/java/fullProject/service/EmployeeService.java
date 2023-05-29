package com.java.fullProject.service;

import com.java.fullProject.EmployeeModel.EmployeesResponse;
import com.java.fullProject.entity.Employees;

import java.util.List;

public interface EmployeeService {

    Employees saveEmployees(Employees employees);

    List<EmployeesResponse> getAllEmployees();

    EmployeesResponse getEmployeeById(int id);

    Employees updateEmployee(Employees employees, int id);

    void deleteEmployee(int id);

    List<Employees> employeesPageable(int offset, int pageSize);

    List<Employees> findEmployeeWithSorting(String field);

    List<Employees> findEmployeeWithPaginationAndSorting(int offset, int pageSize, String field);

}
