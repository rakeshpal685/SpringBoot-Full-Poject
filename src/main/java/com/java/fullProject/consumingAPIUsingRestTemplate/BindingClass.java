package com.java.fullProject.consumingAPIUsingRestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BindingClass {

    //The fields should match with the keys present in the JSON

    private int id;
    private String name;
    private String email;
}
