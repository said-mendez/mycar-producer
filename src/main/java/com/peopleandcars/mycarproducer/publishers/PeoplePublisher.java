package com.peopleandcars.mycarproducer.publishers;

import com.peopleandcars.mycarproducer.config.QueuesConfig;
import com.peopleandcars.mycarproducer.model.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PeoplePublisher implements Publisher<People> {
    private RabbitTemplate rabbitTemplate;

    public PeoplePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(People people) {
        log.info("Publishing people: {}", Thread.currentThread().getName());
        rabbitTemplate.convertAndSend(QueuesConfig.EXCHANGE, QueuesConfig.PEOPLE_ROUTING_KEY, people);
    }

    @Override
    public People generate(String[] row) {
        People people = new People();
        people.setGuid(row[0]);
        people.setFirstName(row[1]);
        people.setLastName(row[2]);
        people.setEmail(row[3]);
        people.setGender(row[4]);
        return people;
    }
}
