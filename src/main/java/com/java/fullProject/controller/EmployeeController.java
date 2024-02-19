package com.java.fullProject.controller;

import com.java.fullProject.employeeModel.EmployeesResponse;
import com.java.fullProject.entities.Employees;
import com.java.fullProject.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empController")
@Slf4j
public class EmployeeController {

  @Autowired private EmployeeService employeeService;

  /*  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }*/

  /*  Here I am using ResponseEntity as the return type because it can give a lot of info back when
  this url is hit, like header, code etc. @PostMapping will map the request to the url and @RequestBody will
  take the  body of the request (json) and add to our method, (Internally DispatcherServlet will convert the json to
  java object and map it to the parameter of the method*/
  @PostMapping(value = "/save", consumes = "application/json")
  /*Here we are using @Valid to validate our data that is saved in the entity,in entity class we have defined
  some validations for the fields, and it will check whether the validations match or not*/
  public ResponseEntity<EmployeesResponse> saveEmployee(@Valid @RequestBody Employees employees) {
    return ResponseEntity.status(HttpStatus.CREATED)
        /*    this way we can send a header back
        .header("headerName", "headerValue")*/
        .body(employeeService.saveEmployees(employees));

    // return new ResponseEntity<>(employeeService.saveEmployees(employees), HttpStatus.CREATED);
    /* We can use (@RequestHeader("name of the header") String VariableName) to capture the header coming in the request,
    Name of the header is optional if VariableName is same as the header name*/

    /*    We can use @ResponseStatus like this also
    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@Valid @RequestBody Employees employees) {
     employeeService.saveEmployees(employees);*/
  }

  //  Using @RequestHeader we can fetch the headers, but using RequestEntity is the best way to
  // fetch multiple request headers and body together
  @PostMapping("/path")
  public ResponseEntity<EmployeesResponse> saveEmployee(RequestEntity<Employees> requestEntity) {
    HttpHeaders headers = requestEntity.getHeaders();
    headers.forEach(
        (key, value) -> {
          log.info(
              String.format(
                  "Header '%s' = '%s", key, value.stream().collect(Collectors.joining("|"))));
        });
    Employees employees = requestEntity.getBody();
    return ResponseEntity.status(HttpStatus.CREATED).body(new EmployeesResponse());
  }

  @GetMapping("/getAll")
  /*  @PreAuthorize("hasAuthority('ADMIN')")
  @PreAuthorize("hasRole('ADMIN')")
  @PreAuthorize("hasAnyRole()")
  We can use these annotations types to tell that the below URL can be accessed by the person with the
  given permissions, this is an alternative to
          .requestMatchers("/empController/getAll").hasAuthority("ADMIN") in securityFilter method of configuration class
          and use @EnableMethodSecurity on top of security configuration class to tell spring that we are using method level security*/
  // Below annotations are for swagger
  @Operation(
      summary = "This is a short summary for the api",
      description =
          "This is a long description about this API", // Description of the controller method.
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Found all employees",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Employees.class))
            }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Unauthorized / Invalid Token",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
      })
  /*@Hidden This is a swagger annotation used to hide this endpoint in the swagger page so that no one can see it*/
  public ResponseEntity<List<EmployeesResponse>> getAllEmployee() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
    /*
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
       OR
    return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    */
  }

  /*PathParameter can be present anywhere in the URl, we just have to keep it inside the {}, /getEmployeeById/{id}/data,
   * we can keep any number of path variables*/
  @GetMapping(
      value = "/getEmployeeById/{id}",
      produces = {
        "application/json",
        "application/xml"
      } // If our project has JAX-B dependency then it will return xml value.
      )
  public ResponseEntity<EmployeesResponse> getEmployeeById(@PathVariable("id") int empid) {
    // Here if the name of the argument in uri is same as the name of the parameter in the method,
    // then we don't need to use the name in @PathVariable

    // Here we are creating our custom header and sending it back to the user.
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("HeaderName", "HeaderValue");
    // Here in the response we are sending back our custom header also.
    return new ResponseEntity<>(
        employeeService.getEmployeeById(empid), responseHeaders, HttpStatus.OK);
  }

  /*  Here we will get ambiguous uri exception because spring will get confused that which uri to fetch, is it the above one with id or the below one with
    name, because their pattern are same. so to resolve this issue we will have to pass params in GetMapping,and now if the uri is /getEmployeeById/{name}?xxx
    then the below uri will be called. we can use similar way for the above uri too.
  @GetMapping(
          value = "/getEmployeeById/{name}",params = "xxx")
  public ResponseEntity<EmployeesResponse> getEmployeeById(@PathVariable String name) {
    //Here if the name of the argument in uri is same as the name of the parameter in the method, then we don't need to use the name in @PathVariable
    return new ResponseEntity<EmployeesResponse>(
            employeeService.getEmployeeByName(name), HttpStatus.OK);
  }*/

  @GetMapping(value = "/getEmployeeById")
  /*Here I will use query parameter while calling the get mapping (http://localhost:8080/empController/getEmployeeById?id=5)
  anything after the ? will be treated as Query parameter and we have to use @RequestParam to capture the data, Query param will be present
  in key:value pair. We can have multiple parameters (they wil be separated by & in the url, http://localhost:8080/empController/getEmployeeById?id=5&userName=rakesh)
  and in that case we have to capture both the parameters using @RequestParam*/
  public ResponseEntity<EmployeesResponse> getEmployeeByIdUsingQueryParam(
      /*Here I am telling that the RequestParameter is not mandatory, it's optional, if the parameter is not given then
       * we can use 1 as default parameter*/
      @RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
    // return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  @GetMapping(value = "/pagination/{offset}/{pageSize}")
  // http://localhost:8080/empController/listPageable/0/2
  public ResponseEntity<List<EmployeesResponse>> employeesPageable(
      @PathVariable int offset, @PathVariable int pageSize) {
    // return new ResponseEntity<>(employeeService.employeesPageable(offset, pageSize),
    // HttpStatus.OK);
    return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.employeesPageable(offset, pageSize));
  }

  //  PutMapping is used when we want to entirely replace the existing data with the new updated
  // one, PatchMapping is used when
  //  we partially want to update the data, in put we have to send the whole object with the updated
  // data
  @PutMapping("/{id}")
  //  We will take the id from the uri and rest we will pass as a json in the body
  public ResponseEntity<Employees> updateEmployee(
      @PathVariable int id, @RequestBody Employees employee) {
    return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employee, id));
  } //  PutMapping is used when we want to entirely replace the existing data with the new updated

  // one, PatchMapping is used when

  //  we partially want to update the data, in patch we don't have to send the whole object, only
  // the fields we want to update in json
  @PatchMapping("/{id}")
  //  We will take the id from the uri and rest we will pass as a json in the body
  public ResponseEntity<Employees> updateLittleBitEmployee(
      @PathVariable int id, @RequestBody Map<String, Object> fields) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.updateLittleBitEmployee(id, fields));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empid) {
    employeeService.deleteEmployee(empid);
    // return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    return ResponseEntity.accepted().build();
  }

  //  Here we are sorting the employees based on the attribute of the employee class that we will
  // pass in the URL
  @GetMapping("/{field}")
  public ResponseEntity<List<Employees>> getEmployeeWithSort(@PathVariable String field) {
    return new ResponseEntity<>(employeeService.findEmployeeWithSorting(field), HttpStatus.OK);
  }

  /*Here we are sorting the employees based on the two attributes of the employee class that we will pass in the URL,
  First the sorting will happen on one attribute and if two entries have the same thing then the result will be
  again sorted based on the second attribute*/
  @GetMapping("/{field}/{status}")
  public ResponseEntity<List<EmployeesResponse>> getEmployeeWithMultipleSort(
      @PathVariable String field, @PathVariable String status) {
    // return new ResponseEntity<>(employeeService.findEmployeeWithSorting(field,status),
    // HttpStatus.OK);
    return ResponseEntity.status(HttpStatus.OK)
        .body(employeeService.findEmployeeWithSorting(field, status));
  }

  // Here we are applying both pagination and sorting
  @GetMapping(value = "/paginationAndSort/{offset}/{pageSize}/{field}")
  // http://localhost:8080/empController/listPageable/0/2
  public ResponseEntity<List<Employees>> employeesPageableAndSorting(
      @PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
    return new ResponseEntity<>(
        employeeService.findEmployeeWithPaginationAndSorting(offset, pageSize, field),
        HttpStatus.OK);
  }

  /* We can use @RequestHeader("name of the header") String VariableName) to capture the header coming in the request,
  "Name of the headers" are optional if VariableName(value1/2 in this case) is same as the header name*/
  @GetMapping(value = "/getEmployeeHeader")
  public ResponseEntity<Map<String, String>> getEmployeeHeader(
      @RequestHeader("name of the header1") String value1,
      @RequestHeader("name of the header2") String value2) {

    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("incomingHeader1", value1);
    requestHeaders.put("incomingHeader2", value2);

    // We can pass this header info to the other layer and do our task
    return ResponseEntity.status(HttpStatus.OK).body(requestHeaders);
  }
}
