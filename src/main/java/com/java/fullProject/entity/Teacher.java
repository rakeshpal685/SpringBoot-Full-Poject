package com.java.fullProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Here we will see @OneToOne and @ManyToOne relationship, JPA says that always try to go for @ManyToOne rather than @OneToMany*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
  @SequenceGenerator(
      name = "teacher_sequence",
      sequenceName = "teacher_sequence",
      allocationSize = 1)
  private long teacherId;

  private String firstName;
  private String lastName;

/*  @OneToMany(cascade=CascadeType.ALL)
  @JoinColumn(name = "teacher_id",referencedColumnName = "teacherId")
*//*  A teacher can teach multiple courses and hence we have created a list of courses and annotated it with
    @OneToMany, A new foreign key will be created in Course Table that will point out to the teacher table
    that which teacher is teaching the particular course.*//*
  private List<Course> courses;*/



}
