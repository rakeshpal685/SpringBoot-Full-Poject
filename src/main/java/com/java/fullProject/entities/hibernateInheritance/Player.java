package com.java.fullProject.entities.hibernateInheritance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/*Here Player is an abstract class that we have used as an entity, the Batsman and Bowler class extends
this class, and both of them are @Entity, but we will see that no specific table is created for
the concrete entity class, only one table is created with the name of abstract class, and it contains
all the information of the concrete entities within (kind of embedded).
In the table we can see a column with the name dtype,which denotes Discriminator Column which means the
given row is from which entity, if we don't provide any type then by default, the type is of the Entity
type. To rename this column we have to use @DiscriminatorColumn().
By default, this inheritance is known as single table strategy, here only a single combined table will be
created for the abstract as well as concrete entity

If we want to change the inheritance strategy and want to create individual table for every entity
then we have to go with @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS). In this case we won't
get a table for the abstract entity and it's properties will be merged with the child entities,
we need DiscriminatorColumn also and the table generation type must be changed to TABLE.

Using strategy=InheritanceType.TABLE_PER_CLASS can impact our performance a bit because we have common
repetitive columns from the parent/abstract entity in all our child entities, to do the things in other
way we have to change the @Inheritance(strategy=InheritanceType.JOINED)and @GeneratedValue(strategy = GenerationType.IDENTITY),
this will trigger a primary key foreign key relationship between our abstract entity and the child
entities and we have different table for all the entities, But this can create a huge impact on the performance
because while fetching data it will use a lot of inner join, outer join, We have very deep inheritance
then this thing can cause performance issue*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
/*@DiscriminatorColumn(name = "hello", discriminatorType = DiscriminatorType.STRING)
Not needed if inheritance strategy is anything other than single table*/
public abstract class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Identity won't work if we are using strategy table per class
    private Integer JerseyNo;
    private String name;
    private Integer NoOfMatches;
}
