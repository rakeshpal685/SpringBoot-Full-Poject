package com.java.fullProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*Here my Course Class has one to one mapping with the CourseMaterial class and I have already defined the
relationship in CourseMaterial class. Now here I have to define CourseMaterial also and provide just
the mapping to make it bidirectional, so that when we call the courseMaterial, it will also pick the
course associated with the material.*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

  @Id
  @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
  private Long courseId;

  private String title;
  private Integer credit;

  @OneToOne(mappedBy = "course")
/* We have OneToOne for the courseMaterial and the course bidirectional mapping, Here CourseMaterial is the primary table
and Course is the secondary table, by default it is unidirectional mapping, means when we fetch the data
about CourseMaterial table, course table data will also come, but the reverse is not possible. Hence to make
the mapping biDirectional I will add a variable of type primary table and add @OneToOne mapping over here and
use mappedBy to tell which foreign key column variable name is linking both the tables, here course is the foreign key in
CourseMaterial.*/
  private CourseMaterial courseMaterial;

/*  Many Courses are taught by any one teacher.
A foreign key will be created in the table which has many associations,because for each row we will have a
primary keys from the associated table, but if we reverse it then for each row in secondary table we have
multiple mappings of the other table and hence we have to create the same row multiple times to map with the
primary keys of the secondary table. Here one teacher can teach multiple courses, so we will create a foreign
key in course table so that for multiple rows of courses we can have same primary key of the teacher. But if
we reverse it then we have to create multiple similar rows of teacher data to hold the unique primary keys
of the course.
Here Teacher is the secondary table and Course is the primary table which will hold the foreign key,
@JoinColumn will be defined on the primary side*/
  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)//ManyToOne has default fetch type as EAGER
  @JoinColumn(name = "teacher_Id", referencedColumnName = "teacherId")
/*teacher_Id is the name of the foreign key column in the DB table, and it is referring to the teacherId column of
the Teacher entity */
  private Teacher teacher;

  /*Many students can be many courses, For @ManyToMany a separate table will be created which will
  store the relationship (primary key), in other mappings only foreign keys were created.
  Here we are going from course as primary to student as secondary.
  We can write the same in student also in that case below joinColumns and inverseJoinColumns will be reversed*/
  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinTable(
      name = "student_course_mapping",// name of the 3rd table
      joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"),//referring to the columns of the 3rd table
      inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"))
  //ManyToMany has default fetch type as LAZY
  /*Here we will use joinTable which will be the table that is created for joining the two entities and
  we will specify which two columns to refer from each entity,
  referencedColumnName is the name of the attribute of the two entities which we want to refer and can
  give any name which will act as column name for the joined table, inverseJoinColumns will specify the other
entity column details, using these two columns hibernate will create a new table which will have two columns
with course_id and student_id as name which will refer to courseId and studentId of the entities respectively.*/
  private List<Student> students;

  public void addStudents(Student student) {
    if (students == null) {
      students = new ArrayList<>();
    }
    students.add(student);
  }
}
