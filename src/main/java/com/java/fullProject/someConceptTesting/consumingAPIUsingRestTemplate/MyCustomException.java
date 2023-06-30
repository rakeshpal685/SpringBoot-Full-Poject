package com.java.fullProject.someConceptTesting.consumingAPIUsingRestTemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Properties;

@NoArgsConstructor
@Data
public class MyCustomException extends RuntimeException {

    private Properties properties;

}
