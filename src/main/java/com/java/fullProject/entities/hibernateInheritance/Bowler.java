package com.java.fullProject.entities.hibernateInheritance;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bowler extends Player{

    private Integer wickets;
    private String best;

    public Bowler(Integer JerseyNo, String name, Integer NoOfMatches, Integer wickets, String best) {
        super(JerseyNo, name, NoOfMatches);
        this.wickets = wickets;
        this.best = best;
    }
}
