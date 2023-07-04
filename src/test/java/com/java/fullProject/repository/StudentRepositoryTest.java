package com.java.fullProject.repository;

import com.java.fullProject.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//This will load all the beans from the context which will be heavy when we are just testing
/*for the repo layer only
@DataJpaTest//use this to test for repo layer, Ideally we should use this method to test our repository, because once the test is completed,
it will flush the data and the database won't be impacted.

If we use the above annotation and try to test our other layers like controller or service then
those beans won't be available here
@Autowired
StudentController studentController;
*/



class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;


 /* @Test
  public void saveStudent() {
    Student student =
        Student.builder()
            .emailId("rakesh@gmail.com")
            .firstName("Rex")
            .lastName("Paul")
             .guardianName("Nik")
             .guardianEmail("niku@gmail.com")
             .guardianMobile("45909090")
            .build();

    studentRepository.save(student);
  }*/



/*  @Test
  public void saveStudentWithGuardianDetails() {

    Guardian guardian =
        Guardian.builder().email("gur@gmail.com").mobile(123560).name("gur").build();

    Student student =
        Student.builder()
            .emailId("rak@gmail.com")
            .firstName("rex")
            .lastName("paul")
            .guardian(guardian)
            .build();

    studentRepository.save(student);
  }*/
    /*
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
  }*/


    @Test
    void getStudentFirstNameByEmail() {
        List<String> studentFirstNameByEmail = studentRepository.getStudentFirstNameByEmail("onpi@gmail.com");
        System.out.println(studentFirstNameByEmail);
    }

    @Test
    void countByFirstName() {
        System.out.println(studentRepository.countByFirstName("mon"));
    }


    @Test
    void findTop2ByFirstNameOrderByStudentIdDesc() {
        System.out.println(studentRepository.findTop2ByFirstNameOrderByStudentIdDesc("mon"));
    }

    @Test
    void getByFirstName() {
        List<StudentRepositoryDTOForFewFields> StudentDTO = studentRepository.getByFirstName("mon");
        StudentDTO.stream().forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
    }

    @Test
    void getByFirstNameNativeQuery() {
        List<StudentRepositoryDTOForFewFields> StudentDTO = studentRepository.getByFirstName("mon");
        StudentDTO.stream().forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
    }

    @Test
    void findByOrderByLastNameDesc() {
        List<Student> Students = studentRepository.findByOrderByLastNameDesc();
        Students.forEach(System.out::println);
    }

/*  @Test
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


  @Test
  void findByGuardianMobileBetween() {
    List<Student> byGuardianMobileBetween = studentRepository.findByGuardianMobileBetween(12345, 12349);
    byGuardianMobileBetween.forEach(System.out::println);
  }

 */
}

