package com.java.fullProject.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
/*
This tells spring that don't create a separate table for this class, just add these values to the other
entity where I will use @Embeddable (Student in our case).
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*We will use this annotation when we have Embeddable classes and the row names are already present in the table,
this is somewhat similar to the @Column(name="student_email") annotation in the entity class.*/
@AttributeOverrides({
  @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
  @AttributeOverride(name = "email", column = @Column(name = "guardian_email")),
  @AttributeOverride(name = "mobile", column = @Column(name = "guardian_mobile"))
})
public class Guardian {

  private String name;
  private String email;
  private String mobile;
}
