package com.java.fullProject.Controller;

import com.java.fullProject.Entity.Employees;
import com.java.fullProject.Service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empController")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

/*  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }*/

/*  Here I am using ResponseEntity as the return type because it can give a lot of info back when
  this url is hit, like header, code etc. @PostMapping wil;l map the request to the url and @RequestBody will
  take the  body of the request (json) and add to our method, (Internally DispatcherServlet will convert the json to 
  java object and map it to the parameter of the method*/  
  @PostMapping(value = "/save",
          consumes = "application/json")
  public ResponseEntity<Employees> saveEmployee(@RequestBody Employees employees) {
    return new ResponseEntity<Employees>(
        employeeService.saveEmployees(employees), HttpStatus.CREATED);
  }

  @GetMapping(value = "/getAll")
  public List<Employees> getAllEmployee() {
    return employeeService.getAllEmployees();
  }

  /*PathParameter can be present anywhere in the URl, we just have to keep it inside the {}, /getEmployeeById/{id}/data*/
  @GetMapping(
          value = "/getEmployeeById/{id}",
          produces = {"application/json", "application/xml"}//If our project has JAX-B dependency then it will return xml value.
  )
  public ResponseEntity<Employees> getEmployeeById(@PathVariable("id") int empid) {
    return new ResponseEntity<Employees>(employeeService.getEmployeeById(empid), HttpStatus.OK);
  }
  @GetMapping(value = "/getEmployeeById")
    /*Here I will use query parameter while calling the get mapping (http://localhost:8080/empController/getEmployeeById?id=5)
     anything after the ? will be treated as Query parameter and we have to use @RequestParam to capture the data, Query param will be present
     in key:value pair. We can have multiple parameters (they wil be separated by & in the url, http://localhost:8080/empController/getEmployeeById?id=5&userName=rakesh)
     and in that case we have to capture both the parameters using @RequestParam*/
  public ResponseEntity<Employees> getEmployeeByIdUsingQueryParam(@RequestParam("id") Integer id) {
    return new ResponseEntity<Employees>(employeeService.getEmployeeById(id), HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<Employees> updateEmployee(
      @PathVariable("id") int empid, @RequestBody Employees employee) {
    return new ResponseEntity<Employees>(
        employeeService.updateEmployee(employee, empid), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empid) {
    employeeService.deleteEmployee(empid);
    return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
  }
}
