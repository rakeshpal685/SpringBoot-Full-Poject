package com.java.fullProject.Controller;

import com.java.fullProject.Entity.Employees;
import com.java.fullProject.Service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

/*  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }*/

  // Here I am using ResponseEntity as the return type because it can give a lot of info back when
  // this
  // url is hit, like header, code etc.
  @PostMapping()
  public ResponseEntity<Employees> saveEmployee(@RequestBody Employees employees) {
    return new ResponseEntity<Employees>(
        employeeService.saveEmployees(employees), HttpStatus.CREATED);
  }

  @GetMapping()
  public List<Employees> getAllEmployee() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("{id}")
  public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") int empid) {
    return new ResponseEntity<Employees>(employeeService.getEmployeeById(empid), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Employees> updateEmployee(
      @PathVariable("id") int empid, @RequestBody Employees employee) {
    return new ResponseEntity<Employees>(
        employeeService.updateEmployee(employee, empid), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empid) {
    employeeService.deleteEmployee(empid);
    return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
  }
}
