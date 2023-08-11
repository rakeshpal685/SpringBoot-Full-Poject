package com.java.fullProject.repository;

import com.java.fullProject.entities.Employees;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepo extends JpaRepository<Employees, Integer> {


    @Query(value = "SELECT date_created FROM Employees  ORDER BY date_updated DESC", nativeQuery = true)
    List<Employees> lastRecord();


    //To select a list of employees where the name contains the specific letter
    @Query(value = "select e FROM Employees e where e.emName like concat('%', ?1, '%')")
    List<Employees> fetchEmployeeByName(String letter);

    @Query(value ="delete from Employees e where e.salary < :salary")
    @Modifying
    @Transactional
    int deleteEmployeeBySalaryLessThan(@Param("salary") double salary);

    /*for sorting and pagination we have to use findAll() method only but by passing Sort and pageable arguments,
    findAll(Sort sort) and findAll(Pageable pagination), hence we don't have to create custom query in repo,
    we can directly use these methods in service.
    */
    
   // @Override
    //Slice<Employees> findAllByEmName(String name,Pageable pageable);
    Page<Employees> findAllByEmName(String name, Pageable pageable);
}