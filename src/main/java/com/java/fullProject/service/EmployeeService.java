package com.java.fullProject.service;

import com.java.fullProject.employeeModel.EmployeesResponse;
import com.java.fullProject.entities.Employees;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    
    EmployeesResponse saveEmployees(Employees employees);

    List<EmployeesResponse> getAllEmployees();

    EmployeesResponse getEmployeeById(int id);

    Employees updateEmployee(Employees employees, int id);

    void deleteEmployee(int id);

    List<EmployeesResponse> employeesPageable(int offset, int pageSize);

    List<EmployeesResponse> findEmployeeWithSorting(String field, String status);

    List<Employees> findEmployeeWithSorting(String field);

    List<Employees> findEmployeeWithPaginationAndSorting(int offset, int pageSize, String field);

    Employees updateLittleBitEmployee(int id, Map<String, Object> fields);
}
