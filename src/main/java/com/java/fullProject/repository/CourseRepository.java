package com.java.fullProject.repository;

import com.java.fullProject.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    /*My defined method for custom pagination.
    Here in pagerequest I have to pass my custom pagerequest like below
    Pageable firstPageTenReords = PageRequest.of(0, 10); */
    Page<Course> findByTitleContaining(String title, Pageable pageRequest);
}
