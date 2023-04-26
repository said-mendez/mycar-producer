package com.peopleandcars.mycarproducer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class People {
    private String guid;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
}
