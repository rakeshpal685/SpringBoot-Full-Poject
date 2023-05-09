package com.java.fullProject.entity;

import jakarta.persistence.*;
import lombok.*;

//CourseMaterial has OneToOne mapping with the Course table.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")

/*I am adding this excluded ToString because I have made one2one mapping and put it lazy loading
and hence for CourseMaterial it won't fetch course and that can cause issue while printing
the CourseMaterial because internally it is calling Course and course table values are not loaded
because of lazy loading Or I can remove the lazy loading and make it Eager.*/

public class CourseMaterial {

  @Id
  @SequenceGenerator(
      name = "courseMaterial_sequence",
      sequenceName = "course_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courseMaterial_sequence")
  private Long courseMaterialId;

  private String url;

  @OneToOne(cascade = CascadeType.ALL,
  fetch = FetchType.LAZY,
  optional = false)
  /*  This denotes that the CourseMaterial will have one to one mapping for the course.
  cascade:- Entity relationships often depend on the existence of another entity, for example the CourseMaterial-Course relationship.
  Without the Course, the CourseMaterial entity doesn't have any meaning of its own. When we delete the Course entity,
  our CourseMaterial entity should also get deleted.Cascading is the way to achieve this.
  When we perform some action on the target entity, the same action will be applied to the associated entity.

  optional says that whenever we are trying to save a CourseMaterial, the Course is mandatory, By default
  the value is true*/

  @JoinColumn(name = "course_Id", referencedColumnName = "courseId")
  /*Note that we place the @OneToOne annotation on the related entity field, Course.
  Also, we need to place the @JoinColumn annotation to configure the name of the column in the
  courseMaterial table that maps to the primary key in the course table.
   If we don't provide a name, Hibernate will follow some rules to select a default one.
  Finally, note in the next entity that we won't use the @JoinColumn annotation there.
  This is because we only need it on the owning side of the foreign key relationship.
  Simply put, whoever owns the foreign key column gets the @JoinColumn annotation.*/
  private Course course;
}
