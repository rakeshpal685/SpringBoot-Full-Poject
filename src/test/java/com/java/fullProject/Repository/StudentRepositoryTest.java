package com.java.fullProject.Repository;

import com.java.fullProject.Entity.Guardian;
import com.java.fullProject.Entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
/*@DataJdbcTest Ideally we should use this method to test our repository, because once the test is completed,
it will flush the data and the database won't be impacted.*/
class StudentRepositoryTest {

  @Autowired private StudentRepository studentRepository;

  @Test
  public void saveStudent() {
    Student student =
        Student.builder()
            .emailId("rak@gmail.com")
            .firstName("Rakesh")
            .lastName("Psl")
            // .guardianName("Nikhil")
            // .guardianEmail("pompom@gmail.com")
            // .guardianMobile("000909090")
            .build();

    studentRepository.save(student);
  }

  @Test
  public void saveStudentWithGuardianDetails() {

    Guardian guardian =
        Guardian.builder().email("abcd@gmail.com").mobile("000909090").name("Naina").build();

    Student student =
        Student.builder()
            .emailId("poka@gmail.com")
            .firstName("Prahar")
            .lastName("Pal")
            .guardian(guardian)
            .build();

    studentRepository.save(student);
  }

  @Test
  public void printStudent() {
    List<Student> studentList = studentRepository.findAll();
    studentList.forEach(System.out::println);
  }
}
