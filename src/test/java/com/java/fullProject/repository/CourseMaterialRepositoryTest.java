/*
package com.java.fullProject.repository;

import com.java.fullProject.entity.Course;
import com.java.fullProject.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*@SpringBootTest//This will load all the beans from the context which will be heavy when we are just testing
for the repo layer only
@DataJpaTest//use this to test for repo layer, Ideally we should use this method to test our repository, because once the test is completed,
it will flush the data and the database won't be impacted.
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
*/
