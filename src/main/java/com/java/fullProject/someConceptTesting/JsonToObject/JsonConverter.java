package com.java.fullProject.someConceptTesting.JsonToObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
/*
If we are using springboot, then it will internally convert the JSON to object and vice versa,
if not then use ObjectMapper's readValue and writeValue for the conversation.
    To convert object to json
        Code code = new Code();
    code.setHex("1111");
    code.setRgba(new String[] {"aaa", "fefe"});
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(code);
    System.out.println(jsonString);
*/

public class JsonConverter {

  public static void main(String[] args) throws IOException {

    String json =
        "[{\"color\":\"black\",\"category\":\"hue\",\"type\":\"primary\",\"code\":{\"rgba\":[255,255,255,1],\"hex\":\"#000\"}},{\"color\":\"white\",\"category\":\"value\",\"code\":{\"rgba\":[0,0,0,1],\"hex\":\"#FFF\"}},{\"color\":\"red\",\"category\":\"hue\",\"type\":\"primary\",\"code\":{\"rgba\":[255,0,0,1],\"hex\":\"#FF0\"}},{\"color\":\"blue\",\"category\":\"hue\",\"type\":\"primary\",\"code\":{\"rgba\":[0,0,255,1],\"hex\":\"#00F\"}},{\"color\":\"yellow\",\"category\":\"hue\",\"type\":\"primary\",\"code\":{\"rgba\":[255,255,0,1],\"hex\":\"#FF0\"}},{\"color\":\"green\",\"category\":\"hue\",\"type\":\"secondary\",\"code\":{\"rgba\":[0,255,0,1],\"hex\":\"#0F0\"}}]";

    File JsonFile =
        new File(
            "C:\\WorkSpace\\springBootPractice\\springBootPractice\\src\\main\\java\\com\\example\\springBootPractice\\Testing\\Sample.json");

    ObjectMapper objectMapper = new ObjectMapper();

    // This is used if we don't want any property from json in our object and still want to
    // construct the object,
    //        or we can use @JsonIgnoreProperties(ignoreUnknown = true) on the top of the converting
    // class.
    // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // To read the json array from the file and get the output as Array. we will use this when we
    // have
    // an array of json objects
    Color[] colors = objectMapper.readValue(JsonFile, Color[].class);

    // To read the json from the file and get the output as list.
    List<Color> list = objectMapper.readValue(JsonFile, new TypeReference<List<Color>>() {});
    /*OR
    List<Color> list= Arrays.asList(objectMapper.readValue(JsonFile, Color[].class));*/

    // To read the json from the file and get the output as Map.
    // Map<String,String> map = objectMapper.readValue(JsonFile, new
    // TypeReference<Map<String,String>>() {});

    // To read the Json from string
    // List<Color> list = objectMapper.readValue(json, new TypeReference<List<Color>>() {});

    list.stream().forEach(c -> System.out.println(c));

    // map.values().forEach(n-> System.out.println(n));
    // list.stream().filter(c->c.getColor().equals("black")).forEach(c->
    // System.out.println(c.getClass().getName()));

    // To write an object to json format
    Color color = new Color();
    color.setColor("Blundi");
    color.setCategory("mustin");
    color.setType("Bald");
    Code code = new Code();
    code.setRgba(new String[] {"12", "4", "43"});
    code.setHex("111111");
    color.setCode(code);

    objectMapper.writeValue(new File("path of the file where you want to store JSON"), color);
  }
}
