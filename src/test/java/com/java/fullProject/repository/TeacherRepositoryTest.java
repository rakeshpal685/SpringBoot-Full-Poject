package com.java.fullProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherRepositoryTest {

  @Autowired private TeacherRepository teacherRepository;

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
  }*/


}
