package com.java.fullProject.entities.hibernateInheritance;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
/*@DiscriminatorValue(value = "MYBatsman")
This will change the name in the discriminator column in the table for the entries that belong to
the below type, if not given then the name will be same as the entity name. It is not needed if
inheritance strategy is anything other than single table in the abstract entity*/

public class Batsman extends Player{

    private Integer battingAvg;
    private Integer fifty;

    public Batsman(Integer JerseyNo, String name, Integer NoOfMatches, Integer battingAvg, Integer fifty) {
        super(JerseyNo, name, NoOfMatches);
        this.battingAvg = battingAvg;
        this.fifty = fifty;
    }
}
