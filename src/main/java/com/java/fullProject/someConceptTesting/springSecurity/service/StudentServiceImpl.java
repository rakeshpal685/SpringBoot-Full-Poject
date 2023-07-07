package com.java.fullProject.someConceptTesting.springSecurity.service;

import com.java.fullProject.someConceptTesting.springSecurity.dto.StudentDTO;
import com.java.fullProject.someConceptTesting.springSecurity.entity.Students;
import com.java.fullProject.someConceptTesting.springSecurity.repository.StudentRepo;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepo studentRepository;

  @Override
  public StudentDTO createStudent(StudentDTO studentDTO) {

    studentDTO.setUserId(UUID.randomUUID().toString());

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    Students studentEntity = modelMapper.map(studentDTO, Students.class);
    studentEntity.setEncrypted_password("test");

    studentRepository.save(studentEntity);

    StudentDTO dto = modelMapper.map(studentEntity, StudentDTO.class);
    return dto;
  }
} 
