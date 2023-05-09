package com.java.fullProject.service;

import com.java.fullProject.entity.Employees;
import com.java.fullProject.exception.customException.ResourceNotFound;
import com.java.fullProject.repository.EmployeeRepo;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  @Autowired private EmployeeRepo employeeRepo;
  /* @Value("${topic.name}")
      private String topicName;
  */
  @Override
  public Employees saveEmployees(Employees employees) {
    return employeeRepo.save(employees);
  }

  @Override
  public List<Employees> getAllEmployees() {
    List<Employees> employeesList = employeeRepo.findAll();
    return employeesList;
  }

  public Employees getEmployeeById(int id) {
    //        Optional<Employees> employees= employeeRepo.findById(id);
    //        if(employees.isPresent()){
    //            return employees.get();
    //        }
    //        else {
    //            throw new ResourceNotFound("Employee", "id", id);
    //        }
    // same in lambda
    return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Employee", "id", id));
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
