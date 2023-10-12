package com.java.fullProject.service;

import com.java.fullProject.employeeModel.EmployeesResponse;
import com.java.fullProject.entities.Employees;
import com.java.fullProject.exception.customException.BusinessException;
import com.java.fullProject.exception.customException.EmployeeNotFound;
import com.java.fullProject.repository.EmployeeRepo;
import java.util.Arrays;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  @Autowired private EmployeeRepo employeeRepo;

  @Autowired
  /*We will use modelMapper to map our entity to the model mapper, for this we have to add a maven dependency
  and create a bean also */
  private ModelMapper modelMapper;

  @Override
  public EmployeesResponse saveEmployees(Employees employees) {
    /*  I can either check the values that are coming here like below or I can use validator dependency to
validate the data, where I put the validation in entity class and in controller method use @Valid*/
    if (employees.getEmName().isBlank() || employees.getEmName().isEmpty()) {
      throw new BusinessException("601", "Please provide a valid employee name");
    }
    try {
      Employees saved = employeeRepo.save(employees);
      return modelMapper.map(saved, EmployeesResponse.class);
      // We can use java's inbuilt BeanUtils to copy the properties too
      // BeanUtils.copyProperties(saved, EmployeesResponse.class);
    } catch (Exception e) {
      throw new BusinessException("602", "Unable to save the employee");
    }
  }

  @Override
  public List<EmployeesResponse> getAllEmployees() {
    List<Employees> employeesList = employeeRepo.findAll();
    List<EmployeesResponse> employeesResponse =
        Arrays.asList(modelMapper.map(employeesList, EmployeesResponse[].class));
    return employeesResponse;
  }

  public EmployeesResponse getEmployeeById(int id) {
    //        Optional<Employees> employees= employeeRepo.findById(id);
    //        if(employees.isPresent()){
    //            return employees.get();
    //        }
    //        else {
    //            throw new ResourceNotFound("Employee", "id", id);
    //        }
    // same in lambda
    Employees employee =
        employeeRepo
            .findById(id)
            .orElseThrow(() -> new EmployeeNotFound("Employee not found in the database"));
    // All the fields of our Employees entity will be mappd to the EmployeeResponse model
    EmployeesResponse employeesResponse = modelMapper.map(employee, EmployeesResponse.class);
    return employeesResponse;
  }

  @Override
  public Employees updateEmployee(Employees updatedEmployeeData, int id) {

    // We need to check if the id is present or not
    Employees existingEmployee =
        employeeRepo
            .findById(id)
            .orElseThrow(() -> new EmployeeNotFound("Employee not found in the database"));

    // Now we will set our updated employee data to the fetched employee above.
    existingEmployee.setEmName(updatedEmployeeData.getEmName());
    existingEmployee.setStatus(updatedEmployeeData.getStatus());
    existingEmployee.setSalary(updatedEmployeeData.getSalary());

    // Now we will save the updated data
    employeeRepo.save(existingEmployee);
    return existingEmployee;
  }

  @Override
  public void deleteEmployee(int id) {
    employeeRepo
        .findById(id)
        .orElseThrow(() -> new EmployeeNotFound("Employee not found in the database"));
    employeeRepo.deleteById(id);
  }

  @Override
  public List<EmployeesResponse> employeesPageable(int offset, int pageSize) {
    List<Employees> content = employeeRepo.findAll(PageRequest.of(offset, pageSize)).getContent();
    return Arrays.asList(modelMapper.map(content, EmployeesResponse[].class));
  }

  /*Here we are taking the field dynamically on which we have to apply sorting, Sort.Direction says in which order the
  values will be sorted*/
  @Override
  public List<EmployeesResponse> findEmployeeWithSorting(String field, String status) {
    /*This way we can sort by two things, first it will sort by field, then if there are two entries
    that are common, then the sorted result will be sorted again by xyz*/
    List<Employees> employeesList =
        employeeRepo.findAll(Sort.by(field).descending().and(Sort.by(status).descending()));
    return Arrays.asList(modelMapper.map(employeesList, EmployeesResponse[].class));
  }

  @Override
  public List<Employees> findEmployeeWithSorting(String field) {
    // return employeeRepo.findAll(Sort.by(Sort.Direction.DESC,field)); We can do it like below
    // also.
    return employeeRepo.findAll(Sort.by(field).descending());
  }

  @Override
  public List<Employees> findEmployeeWithPaginationAndSorting(
      int offset, int pageSize, String field) {
    return employeeRepo
        .findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()))
        .getContent();
  }
}
