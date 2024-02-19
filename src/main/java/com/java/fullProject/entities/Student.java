package com.java.fullProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
/*Here my entity class is extending another entity called BaseEntity, this means that please include the fields of the base class in this
* entity too, we have created a separate base entity so that the base entity contains all the common fields that are required in many other
* entities, hence rather hen repeating the fields again and again in all the entities we are keeping them at a common place, just like java
* parent class*/
public class Student extends BaseEntity{

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

  //This is used to store collection type data for a field. default fetch type is lazy.
  @ElementCollection(fetch = FetchType.EAGER)//This tells hibernate that this is embeddable type.
  @CollectionTable(name = "nicknamesTable", //name of the 3rd table formed
          joinColumns=@JoinColumn(name = "stud_id"))//name of the 3rd table foreign key column
  /*This will tell hibernate that we want to store the list/map of the nickNames in a different table with
table name as nicknamesTable in OneToMAny mapping kind of thing. which have the foreign key as the primary key
of this main table (stud_id is the name of the foreign key which will automatically refer to the @id of this
class as primary key).
By default, the column name would be Classname_Variablename.
Here also we don't have any primary key for the table that is created, it is somewhat similar to the embedded type
but just that here a separate table will be created.*/
  @Column(name = "nick_name")
/*  @MapKeyColumn(name = "nameType")//If we want to store map value then we can use this, this will ack as a key*/
  private List<String> nickNames;


@ManyToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//This is the owner entity in many to many mapping
  private Set<Course> courses;

  @Embedded
  /*This annotation will tell spring that the fields in Guardian class will be added to the Student entity, and don't create a separate entity for Guardian
  * Here guardian is a value field of type Guardian, just like emailId above is a value field of type String,
there won't be nay separate table created for the Guardian, when ever we query for the student, the guardian
will be queried automatically.the embedded class also won't have nay primary key and hence we can't query
anything individually for the Guardian class.
To query anything about the embedded class, we have to go through the main class, like here if we want to fetch
name of the Guardian, then in Jpql we have to write "Student.guardian.name".
* instead of writing @AttributeOverrides in the @Embeddable class we can write it here too*/
  private Guardian guardian;
}
