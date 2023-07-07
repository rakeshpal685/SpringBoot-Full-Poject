package com.java.fullProject.someConceptTesting.springActuator;

import lombok.Data;

import java.util.Map;

@Data
public class ActuatorResponse {

    private Map<String, Object> details;

}
