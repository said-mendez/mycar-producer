package com.peopleandcars.mycarproducer;

import model.Car;
import model.People;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PeoplePublisher {
    private RabbitTemplate rabbitTemplate;

    public PeoplePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String publish(People people) {
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.PEOPLE_ROUTING_KEY, people);

        return people.toString();
    }
}
