package com.java.fullProject.entities.compositPrimaryKey;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "composit_Id_Example")
/*@IdClass(StudentId.class)This class will hold the attributes which will make up my composite primary key.
If we are using @EmbeddedId then we don't have to write this*/
public class CompositKey {

/*    @Id
We want to make a composite primary key. i.e, our table won't have a specific primary key column,
rather it will combine two columns to make a primary key. Hence, we will use @Id on two different attributes of
the class (name and country in our case) and will take the same fields in our @IdClass (StudentId in this case,
Try to avoid this as much as possible.
    @Column(name = "student_name")
   private String name;
    
    @Id
    We want to make a composite primary key and hence we will use @Id on two fields which we want to add and
make a primary key
    private String country;*/

    private Long mobile;

    private int age;

    @EmbeddedId
    /*If we are using @EmbeddedId then we should not use @Id, and we have to remove the field from this entity
class also, because if we keep them then our @Embeddable class will have the same fields, and it will cause
an error*/
    private StudentId studentId;
}
