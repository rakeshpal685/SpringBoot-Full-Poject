package com.java.fullProject.someConceptTesting.springSecurityJWT.service;

import com.java.fullProject.someConceptTesting.springSecurityJWT.dto.StudentDTO;
import com.java.fullProject.someConceptTesting.springSecurityJWT.entity.Authority;
import com.java.fullProject.someConceptTesting.springSecurityJWT.entity.Students;
import com.java.fullProject.someConceptTesting.springSecurityJWT.repository.StudentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

/*  Getting User details to form claims for the JWT token, here i am passing email as userName, if we pass
  something else our repository query will change according to that. We are mapping the student object coming
  from the repo to StudentDTO and returning it to the service */
  @Override
  public StudentDTO getUser(String userName) {
    Students student = studentRepository.findByEmail(userName);
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    StudentDTO studentDto = modelMapper.map(student, StudentDTO.class);
    return studentDto;
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
    /*    Here we will create and return a User object which contains the username, password, and roles.
    the username and password set over here will be compared with the username and password
    used by the user to login, here we are setting the user mailId as the username because user has
    input that only in username while logging. If we want any other field as username,
    set that field here like I used FirstName as username in commented lines which I used during logging*/

    ArrayList<GrantedAuthority> roleList=new ArrayList<GrantedAuthority>();

    List<Authority> roles=studentsDetails.getRoles();
    roles.stream().forEach(r->roleList.add(new SimpleGrantedAuthority(r.getRole())));

/*    return User.withUsername(studentsDetails.getEmail())
        // .withUsername(studentsDetails.getFirstName())
        .password(studentsDetails.getEncrypted_password())
        .authorities("admin")
            .roles(String.valueOf(roles))
        .build();*/

    return new User(studentsDetails.getEmail(),studentsDetails.getEncrypted_password(),roleList);
  }
}
