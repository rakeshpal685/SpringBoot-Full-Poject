package com.java.fullProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
    name = "tbl_student",
    uniqueConstraints = {
      @UniqueConstraint(name = "emailId_unique", columnNames = "email_address"),
      @UniqueConstraint(columnNames = {"firstName"})
    })
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  @SequenceGenerator(
      name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1)
  private Long studentId;

  private String firstName;
  private String lastName;

  @Column(name = "email_address", nullable = false)
  private String emailId;

  @Embedded
  // This annotation will tell spring that the fields in Guardian class will be added to the Student entity.
  private Guardian guardian;
}
