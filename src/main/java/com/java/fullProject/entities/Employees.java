package com.java.fullProject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  // This is for swagger, so that this field is not visible when we are going for post request
  private Integer id;

  @Column(name = "empName")
  private String emName;

  /*  For validation, we need to add either hibernate-validator jar or spring-boot-starter-validation jar
  in pom.xml.
  In out controller's POST request, we have to add @Valid in the method parameter.
  If we try to inset any invalid data,then this validation will fail, and it will throw MethodArgumentNotValidException
  which we have caught in GlobalExceptionHandling class*/
  @Column(name = "status")
  @Size(min = 1, max = 5)
  /* @Min(value = 1, message = "Minimum value should be 1")
  @Max(value = 5, message = "Maximum value should be 5")*/
  private String status;

  @Column(name = "salary")
  @NotNull(message = "The salary cannot be blank")
  /*  Most of the time we don't pass our entity directly, we create a dto or model, hence we have to use
  these validations over there also.*/
  private Integer salary;

  @Column(name = "date_created", updatable = false)
  @CreationTimestamp
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDate date_created;

  @Column(name = "date_updated", insertable = false)
  @UpdateTimestamp
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDate date_updated;
  //  I can take LocalDateTime also to show the full timestamp including time
}
