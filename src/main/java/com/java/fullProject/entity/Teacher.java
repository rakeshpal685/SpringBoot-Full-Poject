package com.java.fullProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

@OneToMany(mappedBy = "teacher",orphanRemoval = true)
/* This is used for biDirectional mapping for @ManyToOne (From one side it will be ManyToOne and from other side
it will be OneToMany). where mappedBy is the variable used in Course Entity as foreign key,"teacher" in our case.
Here Teacher is the secondary table and Course is the primary table(many table) which will hold the foreign key.
We have to set orphanRemoval=true when we want to delete all the associated values from the many table when
I am removing the main record in the primary table.
We are using list here because we have many courses to capture for one Teacher*/
  private List<Course> courses;

  /*@OneToMany(cascade=CascadeType.ALL)
 @JoinColumn(name = "teacher",referencedColumnName = "teacherId")*/
  /*A teacher can teach multiple courses and hence we have created a list of courses and annotated it with
@OneToMany and we will Join it using the foreign key of the many table, i.e private Teacher teacher in our case.
We should always try to use @ManyToOne rather than @OneToMany because if we use OneToMany then for every query
on the primary table row, It will fetch multiple records from the associated secondary table also, if fetch
type is eager, by default it's lazy*/
  /* private List<Course> courses;*/

}
