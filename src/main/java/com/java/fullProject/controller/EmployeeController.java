package com.java.fullProject.controller;

import com.java.fullProject.EmployeeModel.EmployeesResponse;
import com.java.fullProject.entity.Employees;
import com.java.fullProject.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empController")
public class EmployeeController {

  @Autowired EmployeeService employeeService;

  /*  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }*/

  /*  Here I am using ResponseEntity as the return type because it can give a lot of info back when
  this url is hit, like header, code etc. @PostMapping will map the request to the url and @RequestBody will
  take the  body of the request (json) and add to our method, (Internally DispatcherServlet will convert the json to
  java object and map it to the parameter of the method*/
  @PostMapping(value = "/save", consumes = "application/json")
  /*Here we are using @Valid to validate our data that is saved in the entity,in entity we have defined
  some validations for the fields and it will check whether the validations matches or not*/
  public ResponseEntity<Employees> saveEmployee(@Valid @RequestBody Employees employees) {
    return new ResponseEntity<Employees>(
        employeeService.saveEmployees(employees), HttpStatus.CREATED);
  }

  @GetMapping(value = "/getAll")
  // Below annotations are for swagger
  @Operation(summary = "Get all employees") // Description of the controller method.
  @ApiResponses(
      value = { // what to show when we have different status codes.
        @ApiResponse(
            responseCode = "200",
            description = "Found all employees",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Employees.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
      })
  public ResponseEntity<List<EmployeesResponse>> getAllEmployee() {
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
/*    Rather than creating a new ResponseEntity<> object like below we can do this also*/
  }

  /*PathParameter can be present anywhere in the URl, we just have to keep it inside the {}, /getEmployeeById/{id}/data*/
  @GetMapping(
      value = "/getEmployeeById/{id}",
      produces = {
        "application/json",
        "application/xml"
      } // If our project has JAX-B dependency then it will return xml value.
      )
  public ResponseEntity<EmployeesResponse> getEmployeeById(@PathVariable("id") int empid) {
    //Here if the name of the argument in uri is same as the name of the parameter in the method, then we don't need to use the name in @PathVariable
    return new ResponseEntity<EmployeesResponse>(
        employeeService.getEmployeeById(empid), HttpStatus.OK);
  }

  @GetMapping(value = "/getEmployeeById")
  /*Here I will use query parameter while calling the get mapping (http://localhost:8080/empController/getEmployeeById?id=5)
  anything after the ? will be treated as Query parameter and we have to use @RequestParam to capture the data, Query param will be present
  in key:value pair. We can have multiple parameters (they wil be separated by & in the url, http://localhost:8080/empController/getEmployeeById?id=5&userName=rakesh)
  and in that case we have to capture both the parameters using @RequestParam*/
  public ResponseEntity<EmployeesResponse> getEmployeeByIdUsingQueryParam(
      @RequestParam("id") Integer id) {
    return new ResponseEntity<EmployeesResponse>(
        employeeService.getEmployeeById(id), HttpStatus.OK);
  }

  @GetMapping(value = "/pagination/{offset}/{pageSize}")
  // http://localhost:8080/empController/listPageable/0/2
  public ResponseEntity<List<Employees>> employeesPageable(@PathVariable int offset, @PathVariable int pageSize) {
    return new ResponseEntity<List<Employees>>(
            employeeService.employeesPageable(offset, pageSize), HttpStatus.OK);
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


//  Here we are sorting the employees based on the attribute of the employee class that we will pass in the URL
  @GetMapping("/{field}")
  public ResponseEntity<List<Employees>> getEmployeeWithSort(@PathVariable String field) {
    return new ResponseEntity<>(employeeService.findEmployeeWithSorting(field), HttpStatus.OK);
  }

  //Here we are applying both pagination and sorting
  @GetMapping(value = "/paginationAndSort/{offset}/{pageSize}/{field}")
  // http://localhost:8080/empController/listPageable/0/2
  public ResponseEntity<List<Employees>> employeesPageableAndSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
    return new ResponseEntity<List<Employees>>(
            employeeService.findEmployeeWithPaginationAndSorting(offset, pageSize,field), HttpStatus.OK);
  }

}
