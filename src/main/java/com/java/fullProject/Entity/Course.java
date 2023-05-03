package com.java.fullProject.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Here my Course Class has one to one mapping with the CourseMaterial class and I have already defined the
relationship in CourseMaterial class. Now here I have to define CourseMaterial also and provide just
the mapping to make it bi-directional, so that when we call the courseMaterial, it will also pick the
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
  // Here we have to specify that which field in the CourseMaterial entity we have mapped to.
  private CourseMaterial courseMaterial;

  // Many Courses are taught by any one teacher.
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "teacher_Id", referencedColumnName = "teacherId")
  private Teacher teacher;

  /*Many students can be many courses, For @ManyToMany a separate table will be created which will
  store the relationship, in other mappings only foreign keys were created.*/
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "student_course_mapping",
      joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"),
      inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"))
  /*Here we will use joinTable which will be the table that is created for joining the two entities and
  we will specify which two columns to refer from each entity,
  referencedColumnName is the name of the attribute of the two entities which we want to refer and can
  give any name which will act as column name for the joined table, inverseJoinColumns will specify the other entity column details
  Here it meansI will have two columns with course_id and student_id as name which will refer to courseId
  and studentId of the entities respectively.*/
  private List<Student> students;

  public void addStudents(Student student) {
    if (students == null) {
      students = new ArrayList<>();
    }
    students.add(student);
  }
}
