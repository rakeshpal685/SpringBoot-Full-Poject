package com.java.fullProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "employees")
public class Employees {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "empName")
  private String emName;

  @Column(name = "status")
  @Size(min = 1,max = 5)
  private String status;

  @Column(name = "salary")
  @NotBlank(message = "The salary cannot be blank")
/*  Most of the time we don't pass our entity directly, we create a dto or model, hence we have to use
  these validations over there also.*/
  private Integer salary;

  @Column(name = "date_created",updatable = false)
  @CreationTimestamp
  private LocalDate date_created;

  @Column(name = "date_updated",insertable = false)
  @UpdateTimestamp
  private LocalDate date_updated;
}
