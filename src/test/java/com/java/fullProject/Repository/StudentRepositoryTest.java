package com.java.fullProject.Repository;

import com.java.fullProject.Entity.Guardian;
import com.java.fullProject.Entity.Student;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

   // studentRepository.save(student);
  }

  @Test
  public void saveStudentWithGuardianDetails() {

    Guardian guardian =
        Guardian.builder().email("momo@gmail.com").mobile("123456").name("momo").build();

    Student student =
        Student.builder()
            .emailId("mona@gmail.com")
            .firstName("mona")
            //.lastName("bibo")
            .guardian(guardian)
            .build();

    studentRepository.save(student);
  }

  @Test
  public void printStudent() {
    List<Student> studentList = studentRepository.findAll();
    studentList.forEach(System.out::println);
  }

  @Test
  void findByFirstName() {
    List<Student> studentList = studentRepository.findByFirstName("moka");
    studentList.forEach(System.out::println);
  }

  @Test
  void readByLastName() {
    List<Student> studentList = studentRepository.readByLastName("Pal");
    studentList.forEach(System.out::println);
  }

  @Test
  void findByFirstNameOrLastName() {
    List<Student> byFirstNameOrLastName =
        studentRepository.findByFirstNameOrLastName("moka", "Pal");
    byFirstNameOrLastName.forEach(System.out::println);
  }

  @Test
  void findByFirstNameContains() {
    List<Student> nameContains = studentRepository.findByFirstNameContains("k");
    nameContains.forEach(System.out::println);
  }

  @Test
  void findByGuardianName() {
    List<Student> nameContains = studentRepository.findByGuardianName("mom");
    nameContains.forEach(System.out::println);
  }

  @Test
  void getStudentByEmail() {
    List<Student> studentByEmail = studentRepository.getStudentByEmail("moka");
    studentByEmail.forEach(System.out::println);
  }

  @Test
  void getStudentFirstNameByEmail() {
    List<String> studentFirstNameByEmail = studentRepository.getStudentFirstNameByEmail("sonai@gmail.com");
    System.out.println(studentFirstNameByEmail);
  }

  @Test
  void getStudentFirstNameByGuardianName() {
    List<String> name = studentRepository.getStudentFirstNameByGuardianName("lulo");
   name.forEach(System.out::println);
  }

  @Test
  void getStudentFirstNameByGuardianNameNative() {
    List<String> lulo = studentRepository.getStudentFirstNameByGuardianNameNative("lulo");
    lulo.forEach(System.out::println);
  }

  @Test
  void getStudentsByStudentIdAndFirstName() {
    List<Student> kaka = studentRepository.getStudentsByStudentIdAndFirstName(2L, "kaka");
    kaka.forEach(System.out::println);
  }

  @Test
  void getStudentsByStudentIdAndFirstNameNative() {
    List<Student> kaka = studentRepository.getStudentsByStudentIdAndFirstNameNative(3L, "kaka");
    kaka.forEach(System.out::println);
  }

  @Test
  void updateStudentByEmailId() {
    int kaka = studentRepository.updateStudentByEmailId("loli", "sonai@gmail.com");
    System.out.println(kaka);
  }

  @Test
  void testSaveStudent() {}
}
