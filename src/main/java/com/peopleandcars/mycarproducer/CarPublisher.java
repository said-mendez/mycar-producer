package com.peopleandcars.mycarproducer;

import model.Car;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CarPublisher {
    private RabbitTemplate rabbitTemplate;

    public CarPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String publish(Car car) {
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.CAR_ROUTING_KEY, car);

        return car.toString();
    }
}
