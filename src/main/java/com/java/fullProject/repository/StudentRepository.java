package com.java.fullProject.repository;

import com.java.fullProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  /* below are known as Query creation, when JPA doesn't provide the method you are looking for then we can run query like this.
  For more info check this https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details and
  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords,
  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
   Here the name of the method has to be in camel case and must match with the variable name of the entity*/
  List<Student> findByFirstName(String firstName);

  // This is also Query method, we have to take care while writing the name of the method, camel case and variable name.
  List<Student> readByLastName(String lastName);

  List<Student> findByFirstNameOrLastName(String firstName, String lastName);

  List<Student> findByFirstNameContains(String firstName);

  List<Student> findByGuardianName(String name);

  /*  We don't need @Param if the method parameter name and the Query arguments have the same name*/
  //@Query("FROM Student WHERE guardian.mobile BETWEEN :first AND :last")
  List<Student> findByGuardianMobileBetween(Integer first, Integer last);

  /*Now if we are not even satisfied with Query Creation we can pass our own JPQL and SQL queries too,JPQL queries uses
  class's attributes not table, and hence these queries are DB independent.
  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query*/

  /*Here Student is the Entity class name not the table name and emailid is variable name not the table row name
  ?1 maps the first parameter of the method to the placeholder.
  However, rather than placing the values in placeholders like ?1, ?2 ,the better approach is to use named
  params, see below for example*/
  @Query("select s from Student s where s.emailId = ?1")
  List<Student> getStudentByEmail(String email);

  @Query("select s.firstName from Student s where s.emailId = ?1")
  List<String> getStudentFirstNameByEmail(String email);

  @Query("select s.firstName from Student s where s.guardian.name = ?1")
  List<String> getStudentFirstNameByGuardianName(String guardianName);

  /*If we have some complex query that we cannot define by using JPQL then we can use DB native query also
  *we have to use native=true with the query and the field names used in the query will be table column names,
  * while using native query, we don't have to give alias name to the table, the below query can be written as
  *select first_name from tbl_student where guardian_name = ?1 */
  @Query(
      value = "select first_name from tbl_student s where s.guardian_name = ?1",
      nativeQuery = true)
  List<String> getStudentFirstNameByGuardianNameNative(String guardianName);

  /*This is the best approach when we are passing multiple parameters in the query*/
  @Query("Select u from Student u where u.studentId = :id and u.firstName=:name")
  List<Student> getStudentsByStudentIdAndFirstName(
      @Param("id") Long studentId, @Param("name") String firstName);

/*  We don't need @Param if the method parameter name and the Query arguments have the same name, see below example like that*/
  @Query(
      value = "Select * from tbl_student u where u.student_id = :id and u.first_name=:name",
      nativeQuery = true)
  List<Student> getStudentsByStudentIdAndFirstNameNative(
      @Param("id") Long studentId, @Param("name") String firstName);

  /* Whenever we are doing some modification(update/delete). to the DB using a query, we have to use @Modifying annotation and for modification
  we have to open a transaction and commit the changes and hence we will use @Transactional, ideally we have to use @Transactional
  at the service level, so that if out service is calling other repositories also for committing something then all the commits
  will happen under that transaction, this annotation can be used on method or on class*/
  @Query(
      value =
          """
                      UPDATE tbl_student
                      SET first_name =:name
                      WHERE email_address=:mail""",
      nativeQuery = true)
  @Modifying
  @Transactional
  int updateStudentByEmailId(@Param("name") String name, @Param("mail") String email);

/*If in our query there is something which has apostrophe like can't, then we have to put that in a single quote,
else it will throw an error. eg:-@Query(value=" select * from student where name='rakesh's'")*/

/*  Here we are finding sum of all the ages of the students from all the countries grouped by.
  @Query(value = "select s.country, sum(s.age) from student s group by s.country")
  List<Student> getSumOfStudentAgeGroupByCountry();
  instead of sum we can use count(s.id) to get the total number of students from specific countries*/
}
