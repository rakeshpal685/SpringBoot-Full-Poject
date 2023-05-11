package com.java.fullProject.service;

import com.java.fullProject.EmployeeModel.EmployeesResponse;
import com.java.fullProject.entity.Employees;
import com.java.fullProject.exception.customException.ResourceNotFound;
import com.java.fullProject.repository.EmployeeRepo;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  @Autowired private EmployeeRepo employeeRepo;

  @Autowired
  /*We will use modelMapper to map our entity to the model mapper, for this we have to adda maven dependency
  and create a bean also */
  private ModelMapper modelMapper;

  @Override
  public Employees saveEmployees(Employees employees) {
    return employeeRepo.save(employees);
  }

  @Override
  public List<Employees> getAllEmployees() {
    List<Employees> employeesList = employeeRepo.findAll();
    return employeesList;
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
        employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "id", id));
    // All the fields of our Employees entity will be mappd to the EmployeeResponse model
    EmployeesResponse employeesResponse = modelMapper.map(employee, EmployeesResponse.class);
    return employeesResponse;
  }

  @Override
  public Employees updateEmployee(Employees updatedEmployeeData, int id) {

    // We need to check if the id is present or not
    Employees existingEmployee =
        employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "id", id));

    // Now we will set our data from the newly fetched data.
    existingEmployee.setEmName(updatedEmployeeData.getEmName());
    existingEmployee.setStatus(updatedEmployeeData.getStatus());
    existingEmployee.setSalary(updatedEmployeeData.getSalary());

    // Now we will save the updated data
    employeeRepo.save(existingEmployee);
    return existingEmployee;
  }

  @Override
  public void deleteEmployee(int id) {
    employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "id", id));
    employeeRepo.deleteById(id);
  }

  @Override
  public List<Employees> employeesPageable(Pageable pageable) {
    return employeeRepo.findAll(pageable).getContent();
  }
}
