package com.java.fullProject.EmployeeModel;

import lombok.Data;

import java.time.LocalDate;


/*This is the model class that is mapped to the entity, we will never be going to send the entity object
directly to the controller to prevent exposing our DB data directly to outside world via API call*/
@Data
public class EmployeesResponse {
  private Integer id;

  private String emName;

  private String status;

  private Integer salary;

  private LocalDate date_created;

  private LocalDate date_updated;
}
