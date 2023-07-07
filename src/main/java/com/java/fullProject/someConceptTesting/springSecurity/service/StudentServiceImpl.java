package com.java.fullProject.someConceptTesting.springSecurity.service;

import com.java.fullProject.someConceptTesting.springSecurity.dto.StudentDTO;
import com.java.fullProject.someConceptTesting.springSecurity.entity.Students;
import com.java.fullProject.someConceptTesting.springSecurity.repository.StudentRepo;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired PasswordEncoder passwordEncoder;
  @Autowired private StudentRepo studentRepository;

  @Override
  public StudentDTO createStudent(StudentDTO studentDTOObject) {

    studentDTOObject.setUserId(UUID.randomUUID().toString());

    // Mapping the StudentDTO object coming from controller to Student entity Object
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    Students studentEntity = modelMapper.map(studentDTOObject, Students.class);

    /*  String encode = PasswordEncoderFactories.createDelegatingPasswordEncoder()
          .encode(studentDTOObject.getPassword());//This is used to get bcrypt encoder type.
    studentEntity.setEncrypted_password(encode);*/
    /*   OR we can create a bean of PasswordEncoder in configuration class,Here I have created the bean in the
    main config file*/
    String encode1 = passwordEncoder.encode(studentDTOObject.getPassword());
    studentEntity.setEncrypted_password(encode1);

    // Sending the mapped studentEntity to repo layer to save in DB
    studentRepository.save(studentEntity);

    // Mapping the studentEntity object to StudentDto object and sending it back to controller
    StudentDTO studentDtoReturn = modelMapper.map(studentEntity, StudentDTO.class);
    return studentDtoReturn;
  }

  /*  Here I am trying to load the user email,password,authorities from the DB and matching the email against the
  username and password against the password that the user is provided while accessing the endpoint*/
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    /*   Here in the loadUserByUsername method we will capture the username used by the user to login, and
    using that we will fire a query in repo to bring the user data.Here we are using user mail id as username
    If we want any other field as username, use that field for querying, like I used firstname as username
    during logging in the below commented line*/
    Students studentsDetails = studentRepository.findByEmail(username);
    // Students studentsDetails = studentRepository.findByFirstName(username);
    if (studentsDetails == null) {
      throw new UsernameNotFoundException("User with username " + username + " not found");
    }
    /*    Here we will create and return an User object which contains the username and password,
    the username and password set over here will be compared with the username and password
    used by the user to login, here we are setting the user mailId as the username because user has
    input that only in username while logging. If we want any other field as username,
    set that field here like I used FirstName as username in commented lines which i used during logging*/
    return User.withUsername(studentsDetails.getEmail())
        // .withUsername(studentsDetails.getFirstName())
        .password(studentsDetails.getEncrypted_password())
        .authorities("admin")
        .build();
  }
}
