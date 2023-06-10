package com.java.fullProject.JsonToObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
/*This is used so that if we don't want to have a property of the Json to map with the object but still
 want to construct the object.
 If we want to ignore few particular properties then instead of ignoreUnknown
  we can use value={"variable1","variable2"}, this will set the values to null in the object. we can use
  both of the properties together also*/
public class Color {

    private String color;
    private String category;
    private String type;
    private Code code;
    //BOOLEAN
  /*  Objectmapper uses getProperty and setProperty to get and set the values, but for boolean the
    getter/setter will start with is not get, hence it will throw an error for the boolean property,
    to sort this out we will use @JsonProperty("json key name"), now the objectmapper will look into the json
    and take the value of the json key and set it here in this variable.
    We can also use this annotation when our class variable name and the json key name is different*/
    @JsonProperty("isActive")
    private Boolean isActive;

}
