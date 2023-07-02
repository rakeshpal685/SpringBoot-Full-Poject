/*
package com.java.fullProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest//This will load all the beans from the context which will be heavy when we are just testing
for the repo layer only
@DataJpaTest//use this to test for repo layer
class TeacherRepositoryTest {

  @Autowired private TeacherRepository teacherRepository;

 */
/* @Test
  public void saveTeacher() {

    Course courseDBA = Course.builder().title("DBA").credit(5).build();
    Course courseJava = Course.builder().title("Java").credit(6).build();

    Teacher teacher =
        Teacher.builder()
            .firstName("Rakesh")
            .lastName("Mala")
            .courses(List.of(courseDBA, courseJava))
            .build();

    teacherRepository.save(teacher);
  }
}
*/
