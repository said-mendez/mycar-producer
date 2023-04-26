package com.peopleandcars.mycarproducer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
    private String vin;
    private String brand;
    private String model;
    private int year;
    private String color;
}
