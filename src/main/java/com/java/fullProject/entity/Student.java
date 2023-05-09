package com.java.fullProject.entity;

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
//@DynamicInsert
/*When our server is started Hibernate will read our entity class and based on the number of fields
it will keep a default query ready for all type of DB operations keeping the number of fields in mind.
Now let's say that we are inserting a null value to a specific column then also hibernate will consider that
field and create a query, to neglect the null value in query we can use @Dynamic command, We have
@DynamicUpdate and @DynamicInsert*/

@Table(
    name = "tbl_student",
    uniqueConstraints = {
      @UniqueConstraint(name = "emailId_unique", columnNames = "email_address"),
      @UniqueConstraint(columnNames = {"last_name"})
    })
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
  @SequenceGenerator(
      name = "student_sequence", // This is the name of the DB table that will store the sequence.
      sequenceName = "student_sequence", // This name is referred by the generator.
      allocationSize = 1)
  private Long studentId;

  // @Column(unique=true) we can use this also if we have just few unique columns
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email_address", nullable = false)
  private String emailId;

  @Embedded
  // This annotation will tell spring that the fields in Guardian class will be added to the Student
  // entity.
  private Guardian guardian;
}
