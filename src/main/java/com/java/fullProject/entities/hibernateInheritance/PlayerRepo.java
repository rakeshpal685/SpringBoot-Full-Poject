package com.java.fullProject.entities.hibernateInheritance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player,Integer> {
}
