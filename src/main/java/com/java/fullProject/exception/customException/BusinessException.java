package com.java.fullProject.exception.customException;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class BusinessException extends RuntimeException {

  private String errorCode;
  private String errorMessage;
  
  public BusinessException() {
  }
  
  public BusinessException(String errorCode, String errorMessage) {
    super();
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
