package com.peopleandcars.mycarproducer;

import model.Car;

public class Publisher {
    private CarPublisher myCarPublisher;

    public String publish(Car car) {
        return myCarPublisher.publish(car);
    }
}
