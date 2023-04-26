package com.java.fullProject.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/*This annotation is used to tell springframework that this class is an entity,
i.e, create a database table with the given class name and column name as the local fields name.*/
@Entity
/*To give the database table our desired name we use @Table annotation else
boot will give the same name as the class name but in small case.*/
@Table(
    name = "my_product", // name of the table given by me.
    uniqueConstraints = { // If we want to make a column that must have unique values then we can do
                          // it like this.
      @UniqueConstraint(
          name = "sku_unique", // Here we can give any name.
          columnNames =
              "stock_keeping_unit" // This is the name of the column that will have unique values.
          ),
      /*                @UniqueConstraint( //This way we can create multiple unique constraints.
              name ="my_unique",
              columnNames = "name"
      )*/
    })
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product {

  @Id // This defines that the below column is the primary key of the table.
  /*The @GeneratedValue defines how we will generate the primary key value.The strategy attribute is used to specify the primary key generation strategy
  that the persistence provider must use to generate the annotated entity primary key.It is an optional attribute. Strategy values are defined in
  javax.persistence.GeneratorType enumeration which are as follows:
  1. AUTO: This is the default type, this let the database vendor to choose the generation strategy, most DB vendors will choose sequence for id generation.
   In MySQL DB, hibernate will create a table called "hibernate_sequence" and keep the latest value there and increment it when new values are added.
  2. IDENTITY: In this case database is responsible for determining and assigning the next primary key, it will see the primary key of the previous record
   and increment it for the next record. But this is not good for JDBC batch operations.
  3. SEQUENCE: A sequence specify a database object that can be used as a source of primary key values. It uses @SequenceGenerator. It is most commonly used
  in large scale applications, and we should consider using this only. here hibernate will create a sequence table by the name provided by us.
  4. TABLE: It keeps a separate table with the primary key values. It uses @TableGenerator. This strategy is not used anymore.
   Note: Default value of Strategy attribute is AUTO.
  */
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
  // Here we are telling to use the below sequence generator by referring the name of the sequence.
  @SequenceGenerator( // If we don't provide this then hibernate will automatically generate a
                      // sequence.
      name =
          "product_generator", // This is the name of the sequence generator which is used in
                               // @GeneratedValue to tell which sequence is used.
      sequenceName =
          "product_sequence_name", // This is the name of the sequence table that hibernate will
                                   // create to store the generated sequence.
      allocationSize = 1 // This means that the sequence will be incremented by 1.
      )
  private Long id;

  @Column(name = "stock_keeping_unit", nullable = false)
  // This was we can change the name of the column in the database.
  private String sku;

  @Column(nullable = false, length = 20)
  // This ensures that the name column is not null. We can specify the size of the column by using
  // length.
  private String name;

  /*@Column(unique = true)We can use unique over here to set this column's value as a unique, rather than setting it
 in the @Table above, this is useful if we have one or two unique column */
  private String description;
  private BigDecimal price;
  private boolean active;
  private String imageUrl;

  @CreationTimestamp
  // This annotation is provided by hibernate, and it will get the current timestamp from JVM and
  // assign it to the field.
  private LocalDateTime dateCreated;

  @UpdateTimestamp
  // This annotation is provided by hibernate,it will get the timestamp automatically when the field
  // was updated.
  private LocalDateTime lastUpdated;
}
