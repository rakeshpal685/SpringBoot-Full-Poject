package com.java.fullProject.someConceptTesting.springSecurityJWT.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "jwtstudents")
public class Students {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String email;

  private String encrypted_password;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(unique = true, nullable = false)
  private String userId;

  @OneToMany(mappedBy = "student_id",fetch = FetchType.EAGER)
  private List<Authority> roles;


}
