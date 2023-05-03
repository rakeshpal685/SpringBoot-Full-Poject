package com.java.fullProject.Repository;

import com.java.fullProject.Entity.Course;
import com.java.fullProject.Entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {

  @Autowired private CourseMaterialRepository courseMaterialRepository;

  @Test
  public void saveCourseMaterial() {

    Course course = Course.builder()
            .credit(4)
            .title(".net")
            .build();

    CourseMaterial courseMaterial =
        CourseMaterial.builder().url("www.rakesh.com")
                .course(course)
                .build();

    courseMaterialRepository.save(courseMaterial);
  }

  @Test
  public void printAllCourseMaterials(){
    courseMaterialRepository.findAll().forEach(System.out::println);
  }

}
