package com.java.fullProject.someConceptTesting.springSecurity.controller;

import com.java.fullProject.someConceptTesting.springSecurity.dto.StudentDTO;
import com.java.fullProject.someConceptTesting.springSecurity.model.StudentRequestModel;
import com.java.fullProject.someConceptTesting.springSecurity.model.StudentResponseModel;
import com.java.fullProject.someConceptTesting.springSecurity.service.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @Autowired private StudentService studentService;

  @GetMapping("/test")
  public String getStudent(){
    return "Hello";
  }

  @PostMapping("/students")
  /*    The @Valid annotation is used to trigger the validation that we have put when we will receive the
  values in StudentRequestModel*/
  //Passing the Json object to StudentRequestModel
  public ResponseEntity<StudentResponseModel> createStudent(
      @Valid @RequestBody StudentRequestModel requestModelObject) {

    //Creating a modelMapper and mapping the fields from StudentRequestModel to StudentDTO
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // When source and destination fields have same name
    StudentDTO studentDTOObject = modelMapper.map(requestModelObject, StudentDTO.class);

    //Sending the mapped StudentDTOObject to the repo layer
    StudentDTO studentDtoReturn = studentService.createStudent(studentDTOObject);

    //Converting the StudentDto response from the service layer to StudentResponseModel
    StudentResponseModel response = modelMapper.map(studentDtoReturn, StudentResponseModel.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
