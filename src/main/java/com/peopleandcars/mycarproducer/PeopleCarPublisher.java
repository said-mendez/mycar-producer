package com.peopleandcars.mycarproducer;

import model.PeopleCar;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PeopleCarPublisher {
    private RabbitTemplate rabbitTemplate;

    public PeopleCarPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String publish(PeopleCar peopleCar) {
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.PEOPLE_CAR_ROUTING_KEY, peopleCar);

        return peopleCar.toString();
    }
}
