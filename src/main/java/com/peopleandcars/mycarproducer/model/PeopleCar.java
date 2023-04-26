package com.peopleandcars.mycarproducer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PeopleCar {
    private String uuid;
    private String peopleId;
    private String carId;
}
