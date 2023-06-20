package com.java.fullProject.employeeModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/*This is the model class that is mapped to the entity, we will never be going to send the entity object
directly to the controller to prevent exposing our DB data directly to outside world via API call*/
@Data
public class EmployeesResponse {
  private Integer id;

  private String emName;

  @Size(min = 1,max = 5)
  private String status;

  //see the entity class for more information
  @NotNull(message = "The salary cannot be blank")
  private Integer salary;

  private LocalDate date_created;

  private LocalDate date_updated;
}
