package com.java.fullProject.Repository;

import com.java.fullProject.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employees, Integer> {


    @Query("SELECT date_created FROM Employees  ORDER BY date_updated DESC")
    List<Employees> lastRecord();
}
