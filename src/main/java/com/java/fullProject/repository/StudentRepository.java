package com.java.fullProject.repository;

import com.java.fullProject.entities.Student;
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

   Here the name of the method has to be in camel case and must match with the variable name of the entity,
   WHILE USING JPQL WE WILL USE ENTITY NAME AND VARIABLES NAME, IN NATIVE QUERY WE WILL USE TABLE NAME AND COLUMN NAME,
   Before writing your custom query just check once that the query is present in boot Query or not
   
   query creation- find[limit]by[ property(s)][comparison][ordering] -
 */
  List<Student> findByFirstName(String firstName);
/*  We can use getByXyz also
List<Student> getByFirstName(String firstName); This will also return the same result as findBy method, we can use getBy,StreamBy also
findAllByFirstName will also return the same result*/

  // This is also Query method, we have to take care while writing the name of the method, camel case and variable name.
  List<Student> readByLastName(String lastName);

  List<Student> findByFirstNameOrLastName(String firstName, String lastName);

  List<Student> findByFirstNameContains(String firstName);

  List<Student> findByGuardianName(String name);

  /*List of all the Students sorted in descending order of Last name, This is called static sorting,
because in the method only we are specifying on which column we have to sort the result,
we must use dynamic sorting in which we will pass the field name through url during runtime to the
controller, on which we want to sort the result, eg:- getEmployeeWithSort method in EmployeeController*/
  List<Student> findByOrderByLastNameDesc();

  /*  We don't need @Param if the method parameter name and the Query arguments have the same name*/
  //@Query("FROM Student WHERE guardian.mobile BETWEEN :first AND :last")
  List<Student> findByGuardianMobileBetween(Integer first, Integer last);

  /*Returns the number of Student with the given first name*/
  long countByFirstName(String firstName);

  /*Now if we are not even satisfied with Query Creation we can pass our own JPQL and SQL queries too,JPQL queries uses
  class's attributes not table, and hence these queries are DB independent.
  https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query*/

  /*Here Student is the Entity class name not the table name and emailid is variable name not the table row name
  ?1 maps the first parameter of the method to the placeholder.
  However, rather than placing the values in placeholders like ?1, ?2 ,the better approach is to use named
  params, see below getStudentsByStudentIdAndFirstName() for example, try to avoid '?' as much as possible and use ':'
  because of SQL injection issue*/
  @Query("select s from Student s where s.emailId = ?1")
  //@Query("from Student s where s.emailId = ?1") select is optional, and we can replace the above query with this
  List<Student> getStudentByEmail(String email);

  @Query("select s.firstName from Student s where s.emailId = ?1")
  List<String> getStudentFirstNameByEmail(String email);

  @Query("select s.firstName from Student s where s.guardian.name = ?1")
  List<String> getStudentFirstNameByGuardianName(String guardianName);

  /*WHILE USING JPQL WE WILL USE ENTITY NAME AND VARIABLES NAME, IN NATIVE QUERY WE WILL USE TABLE NAME AND COLUMN NAME
  If we have some complex query that we cannot define by using JPQL then we can use DB native query also
  *we have to use native=true with the query and the field names used in the query will be table column names,
  * while using native query,it is not necessary to give alias name to the table, but it's a good practice to do so,
  *the below query can be written as
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

  //First 2 most costly products or first 2 employees taking most salary
     List<Student> findTop2ByFirstNameOrderByStudentIdDesc(String name);

     //List<Student> findLastNameByStudentId(int id); not working

/*Here we are using a separate interface called StudentRepositoryDTOForFewFields which have two fields
of the StudentRepository, so we can get all the values of the Student and map it to this interface to
get just the fields specified in the new interface.See the test for the output.
While using native query this might cause an error, so we have to write the native query with alias name
"select first_name as firstName, last_name as lastname from Student s where s.....".Here we have to use
column name as usual in native query but for alias we have to use the property name of the main repo
i.e, StudentRepository. See below getByFirstNameNativeQuery method*/
  List<StudentRepositoryDTOForFewFields> getByFirstName(String firstName);

  @Query(value = "select first_name as firstName, last_name as lastName from tbl_student s where s.first_name=?1", nativeQuery = true)
  List<StudentRepositoryDTOForFewFields> getByFirstNameNativeQuery(String firstName);
  
/*  //To find all the records in ascending order of the variable name
  findAllByOrderByVariable_Name();
  
  //To find all the records in descending order of the variable name
  findAllByOrderByVariable_NameDesc();*/
  
}
