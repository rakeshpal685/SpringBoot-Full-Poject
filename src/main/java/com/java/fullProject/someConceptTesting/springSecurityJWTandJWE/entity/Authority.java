package com.java.fullProject.someConceptTesting.springSecurityJWTandJWE.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "jwtstudentsauthorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roles")
    private String role;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students student_id;
}
