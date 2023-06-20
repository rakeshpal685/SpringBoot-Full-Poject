package com.java.fullProject.entity.compositPrimaryKey;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ManyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    @ManyToOne
    @JoinColumns(
            value={
                    @JoinColumn(name = "country_fk", referencedColumnName = "country"),
                    @JoinColumn(name = "name_fk", referencedColumnName = "name")
                  }
                )
    /*Here in our many table we will have the foreign key which will be denoting the primary key of the CompositeKey
Entity, but our CompositeKey entity have Composite primary key (mixture of two different fields), hence we also
have to put two foreign key columns in our many class which denotes the composite primary keys.
Here name is the name of the foreign key column and referencedColumnName denotes which column it is
referring to as the primary key of the other entity */
    private CompositKey compositKey;
}
