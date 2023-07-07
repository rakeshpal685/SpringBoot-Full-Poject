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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @Autowired private StudentService studentService;

  @PostMapping("/students")
  /*    The @Valid annotation is used to trigger the validation that we have put when we will receive the
  values in StudentRequestModel*/
  public ResponseEntity<StudentResponseModel> createStudent(
      @Valid @RequestBody StudentRequestModel requestModel) {

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // When source and destination fields have same name
    StudentDTO studentDTO = modelMapper.map(requestModel, StudentDTO.class);

    StudentDTO dto = studentService.createStudent(studentDTO);

    StudentResponseModel response = modelMapper.map(dto, StudentResponseModel.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
