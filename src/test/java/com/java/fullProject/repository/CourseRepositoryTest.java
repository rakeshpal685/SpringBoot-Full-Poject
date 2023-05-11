/*
package com.java.fullProject.repository;

import com.java.fullProject.entity.Course;
import com.java.fullProject.entity.Guardian;
import com.java.fullProject.entity.Student;
import com.java.fullProject.entity.Teacher;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class CourseRepositoryTest {

  @Autowired private CourseRepository courseRepository;

  @Test
  public void printCourses() {
    courseRepository.findAll().forEach(System.out::println);
  }

  @Test
  public void saveCourseWithTeacher() {

    Teacher teacher = Teacher.builder().firstName("Priyanka").lastName("Singh").build();

    Course course = Course.builder().title("Python").credit(6).teacher(teacher).build();

    courseRepository.save(course);
  }

  @Test
  public void findAllPagination() {
    Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

    Pageable secondPageWithFiveElements = PageRequest.of(1, 5);

    List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();

    Long TotalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

    int TotalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
    */
/*int TotalPages2 = courseRepository.findAll(secondPageWithFiveElements).getTotalPages();
    System.out.println("Total pages: " + TotalPages2);*//*


    System.out.println("Total pages: " + TotalPages);

    System.out.println("Total elements: " + TotalElements);

    System.out.println("Courses = " + courses);
  }

  @Test
  public void findAllWithSorting() {
    Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));

    Pageable sortByDescendingCredit = PageRequest.of(0, 2, Sort.by("credit").descending());

    Pageable sortByTitleAndCreditDesc =
        PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));

    List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
    System.out.println(courses);
  }

  @Test
  public void findByTitleContaining() {
    Pageable firstPageTenReords = PageRequest.of(0, 10);

    List<Course> courses =
        courseRepository.findByTitleContaining("D", firstPageTenReords).getContent();
    System.out.println("Courses :" + courses);
  }

  @Test
  public void saveCourseWithStudentAndTeacher() {
     Guardian guardian = Guardian.builder().name("Papa").mobile("123").email("ygz@gmail.com").build();
    Teacher teacher = Teacher.builder().firstName("raghu").lastName("Nath").build();
    Student student =
        Student.builder()
            .firstName("Rakesh")
            .lastName("Palo")
            .emailId("rak@gmail.com")
             .guardian(guardian)
            .build();
    Course course = Course.builder().title("AI").credit(12).teacher(teacher).build();

    course.addStudents(student);

    courseRepository.save(course);
  }
}
*/
