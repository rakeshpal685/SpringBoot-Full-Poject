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
This tells spring that don't create a separate table for this class and hence no primary key will be there,
just add these values to the other entity where I will use @Embeddable (Student in our case).
The lifecycle of this class is same as the main entity, and won't have its own lifecycle.
If the main entity data from the db is deleted this class's data will also get deleted.
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*We will use this annotation when we have Embeddable classes and the row names are already present in the table,
this is somewhat similar to the @Column(name="student_email") annotation in the entity class. We can write theis
in the @Embedded location too instead of here*/
@AttributeOverrides({
  @AttributeOverride(name = "name", column = @Column(name = "guardian_name")),
  @AttributeOverride(name = "email", column = @Column(name = "guardian_email")),
  @AttributeOverride(name = "mobile", column = @Column(name = "guardian_mobile"))
})

/*@Entity, here we won't use entity because we don't want to create a separate table in the DB, we just want to
add these fields to my @Embedded table, this is done so that let's say we have few other tables also which to
need this Guardian values, so it's better to create a Guardian class separately and embed it wherever necessary,
rather than declaring the values again and again in all the entities, We also don't have id here*/
public class Guardian {

/*  @Column(name = "guardian_name"), We can use this also, if we are creating this embeddable table by ourselves,
  else we have to use @AttributeOverride as above if the table is already created by someone else*/
  private String name;
  private String email;
  private Integer mobile;

/*
  When should we go for a separate table and when should we go for embedded entity, if our second entity is very
  important, and we want to use it as an independent entity then go for separate table, else if the second table
  is just required for my first table to de-clutter the values, then go for embedded tables.*/
}
